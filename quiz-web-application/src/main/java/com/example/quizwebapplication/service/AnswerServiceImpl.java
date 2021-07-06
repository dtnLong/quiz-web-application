package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dto.Error;
import com.example.quizwebapplication.dto.SaveAnswerRequest;
import com.example.quizwebapplication.dto.SaveAnswerResponse;
import com.example.quizwebapplication.entity.Answer;
import com.example.quizwebapplication.entity.Group;
import com.example.quizwebapplication.entity.QuizCode;
import com.example.quizwebapplication.repository.AnswerRepository;
import com.example.quizwebapplication.repository.GroupRepository;
import com.example.quizwebapplication.repository.LoginRepository;
import com.example.quizwebapplication.repository.QuizCodeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final QuizCodeRepository quizCodeRepository;
    private final GroupRepository groupRepository;
    private final LoginRepository loginRepository;
    private final AuthenticationService authenticationService;

    @Transactional
    @Override
    public SaveAnswerResponse saveAnswers(SaveAnswerRequest providedAnswers, String token) {
        SaveAnswerResponse response = new SaveAnswerResponse();
        Optional<QuizCode> quizCode = quizCodeRepository.findById(providedAnswers.getQuizCode());
        Optional<Group> group = groupRepository.getGroupByName(providedAnswers.getGroupName());

        // Check if group and quiz code is available
        if (group.isEmpty()) {
            response.getErrors().add(new Error("not_found", "Group not found"));
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setSuccess(false);
            return response;
        }

        if (quizCode.isEmpty()) {
            response.getErrors().add(new Error("not_found", "Quiz not found"));
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setSuccess(false);
            return response;
        }

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
            answer.setQuizCode(quizCode.get());
            answer.setGroup(group.get());
            answer.setAnswerChoice(providedAnswers.getAnswers().get(i).getAnswer());
            answer.setQuestionNumber(providedAnswers.getAnswers().get(i).getQuestionNumber());
            answerRepository.save(answer);
        }

        // Increase attempt of the submit group, update latest quiz to current quiz and update submitted
        groupRepository.addAttempt(providedAnswers.getGroupName());
        groupRepository.updateLatestQuiz(providedAnswers.getQuizCode(), providedAnswers.getGroupName());
        loginRepository.updateSubmitted(providedAnswers.getGroupName(), providedAnswers.getQuizCode(), true);

        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        return response;
    }

}
