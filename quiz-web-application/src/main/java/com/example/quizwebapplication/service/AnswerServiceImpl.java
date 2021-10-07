package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dto.*;
import com.example.quizwebapplication.dto.Error;
import com.example.quizwebapplication.entity.*;
import com.example.quizwebapplication.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuizCodeRepository quizCodeRepository;
    private final GroupRepository groupRepository;
    private final LoginRepository loginRepository;
    private final AuthenticationService authenticationService;
    private final QuizRepository quizRepository;
    private final QuizEncodeRepository quizEncodeRepository;
    private final MailService mailService;

    private Map<Long, Quiz> mapQuizList(List<Quiz> quizCorrectAnswerList) {
        Map<Long, Quiz> quizCorrectAnswerMap = new HashMap<>();
        for (Quiz correctAnswer:quizCorrectAnswerList) {
            quizCorrectAnswerMap.put(correctAnswer.getQuestionNumber(), correctAnswer);
        }
        return quizCorrectAnswerMap;
    }

    private String composeMail(SaveAnswerRequest providedAnswers) {
        StringBuilder content = new StringBuilder();
        content.append("Group name: ")
                .append(providedAnswers.getGroupName())
                .append("\n")
                .append("Quiz code: ")
                .append(providedAnswers.getQuizCode())
                .append("\n");
        for (int i = 0; i < providedAnswers.getAnswers().size(); i++) {
            content.append(providedAnswers.getAnswers().get(i).getQuestionNumber())
                    .append(". ")
                    .append(providedAnswers.getAnswers().get(i).getAnswer())
                    .append("\n");
        }
        return content.toString();
    }

    @Transactional
    @Override
    public String sendAnswerMail(SaveAnswerRequest providedAnswers) {
        String errorMessage = "";
        // Send email
        String recipientAddress = groupRepository.getGroupByName(providedAnswers.getGroupName()).get().getLeaderEmail();
        String senderAddress = "thelogisticomseason6@gmail.com";
        String subject = "Answer submitted";
        String content = composeMail(providedAnswers);
        try {
            mailService.sendEmail(senderAddress, recipientAddress, subject, content);
        } catch (MailSendException exception) {
            errorMessage = exception.getMessage();
        }
        return errorMessage;
    }

    @Transactional
    @Override
    public SaveAnswerResponse saveAnswers(SaveAnswerRequest providedAnswers, String token) {
        SaveAnswerResponse response = new SaveAnswerResponse();
        Optional<QuizEncode> quizEncode = quizEncodeRepository.findById(providedAnswers.getQuizCode());
        if (quizEncode.isEmpty()) {
            response.getErrors().add(new Error("not_found", "Quiz not found"));
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setSuccess(false);
            return response;
        }
        String quizCode = quizEncode.get().getQuizCode().getCode();

        //Optional<QuizCode> quizCode = quizCodeRepository.findById(providedAnswers.getQuizCode());
        Optional<Group> group = groupRepository.getGroupByName(providedAnswers.getGroupName());

        // Check if group and quiz code is available
        if (group.isEmpty()) {
            response.getErrors().add(new Error("not_found", "Group not found"));
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setSuccess(false);
            return response;
        }

//        if (quizCode.isEmpty()) {
//            response.getErrors().add(new Error("not_found", "Quiz not found"));
//            response.setStatus(HttpStatus.NOT_FOUND.value());
//            response.setSuccess(false);
//            return response;
//        }

        // Handle token not exist
        if (token == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setSuccess(false);
            response.getErrors().add(new Error("unauthorized", "Unauthorized access"));
            return response;
        }

        // Handle invalid token
        if (!authenticationService.verifyToken(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setSuccess(false);
            response.getErrors().add(new Error("unauthorized", "Invalid token"));
            return response;
        }

        // Handle group name in token and quiz code in token don't match provided group name and provided quiz code
        String quizCodeToken = (String) authenticationService.getCustomClaim(token, "quizCode");
        String groupNameToken = (String) authenticationService.getCustomClaim(token, "groupName");
        if (!providedAnswers.getQuizCode().equals(quizCodeToken) || !providedAnswers.getGroupName().equals(groupNameToken)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setSuccess(false);
            response.getErrors().add(new Error("unauthorized", "Group name and quiz code mismatch"));
            return response;
        }

        // Check if answer already submitted
        if (loginRepository.findSubmittedByNameAndQuizCode(providedAnswers.getGroupName(), providedAnswers.getQuizCode()).get()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setSuccess(false);
            response.getErrors().add(new Error("unauthorized", "Answer already submitted"));
            return response;
        }

        // Save answers to database
        for (int i = 0; i < providedAnswers.getAnswers().size(); i++) {
            Answer answer = new Answer();
            answer.setQuizCode(quizEncode.get().getQuizCode());
            answer.setGroup(group.get());
            answer.setAnswerChoice(providedAnswers.getAnswers().get(i).getAnswer());
            answer.setQuestionNumber(providedAnswers.getAnswers().get(i).getQuestionNumber());
            answerRepository.save(answer);
        }

        // Increase attempt of the submit group, update latest quiz to current quiz and update submitted
        groupRepository.addAttempt(providedAnswers.getGroupName());
        groupRepository.updateLatestQuiz(quizCode, providedAnswers.getGroupName());
        loginRepository.updateSubmitted(providedAnswers.getGroupName(), providedAnswers.getQuizCode(), true);

        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        return response;
    }

    @Transactional
    @Override
    public MarkingResponse markAnswer(String groupName) {
        MarkingResponse response = new MarkingResponse();
        response.setGroupName(groupName);
        // Mark answer of latest quiz by group
        Optional<Group> group = groupRepository.getGroupByName(groupName);
        if (group.isEmpty()) {
            response.setSuccess(false);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.getErrors().add(new Error("not found", "Group name not found!"));
            return response;
        }
        String quizCode = group.get().getQuizCode().getCode();
        if (quizCode == null) {
            response.setSuccess(false);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.getErrors().add(new Error("not found", "Group haven't taken any quiz"));
            return response;
        }
        response.setQuizCode(quizCode);

        Sort sort = Sort.by("questionNumber").ascending();
        Optional<List<Quiz>> quizCorrectAnswersList = quizRepository.getCorrectAnswerByQuizCode(quizCode, sort);
        Optional<List<Answer>> groupAnswer = answerRepository.findAnswerByGroupNameAndQuizCode(groupName, quizCode);
        Map<Long, Quiz> quizCorrectAnswersMap = mapQuizList(quizCorrectAnswersList.get());

        // Begin marking
        long score = 0;
        for (Answer answer:groupAnswer.get()) {
            ScoreResponseFormat scoreResponseFormat = new ScoreResponseFormat();
            if (quizCorrectAnswersMap.get(answer.getQuestionNumber()) == null) {
                response.setSuccess(false);
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.getErrors().add(new Error("internal server", "Question number not in quiz"));
                response.setAnswerList(new ArrayList<>());
                return response;
            }
            Long currentQuestion = answer.getQuestionNumber();
            String answerChoice = answer.getAnswerChoice();
            String correctChoice = quizCorrectAnswersMap.get(currentQuestion).getQuestionChoice().getOption();

            scoreResponseFormat.setQuestionNumber(currentQuestion);
            scoreResponseFormat.setProvidedAnswer(answerChoice);
            scoreResponseFormat.setCorrectAnswer(correctChoice);
            if (answerChoice != null && answerChoice.equals(correctChoice)) {
                score++;
                scoreResponseFormat.setCorrect(true);
            } else {
                scoreResponseFormat.setCorrect(false);
            }
            response.getAnswerList().add(scoreResponseFormat);
        }

        // Saving score
        groupRepository.updateScore(groupName, score);
        response.setScore(score);
        response.setSuccess(true);
        response.setStatus(HttpStatus.OK.value());
        return response;
    }
}
