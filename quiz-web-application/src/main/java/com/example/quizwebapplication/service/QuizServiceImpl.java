package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dto.ChoiceResponseFormat;
import com.example.quizwebapplication.dto.Error;
import com.example.quizwebapplication.dto.GetQuizResponse;
import com.example.quizwebapplication.dto.QuestionResponseFormat;
import com.example.quizwebapplication.dto.QuizFormat;
import com.example.quizwebapplication.entity.Quiz;
import com.example.quizwebapplication.entity.QuizEncode;
import com.example.quizwebapplication.repository.LoginRepository;
import com.example.quizwebapplication.repository.QuizCodeRepository;
import com.example.quizwebapplication.repository.QuizEncodeRepository;
import com.example.quizwebapplication.repository.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuizCodeRepository quizCodeRepository;
    private final AuthenticationService authenticationService;
    private final LoginRepository loginRepository;
    private final QuizEncodeRepository quizEncodeRepository;

    private QuizFormat parseQuiz(List<Quiz> unformattedQuiz, String quizCode) {
        // Parse quiz obtain from database to correct format
        QuizFormat quizFormat = new QuizFormat();
        QuestionResponseFormat questionResponseFormat = new QuestionResponseFormat();
        quizFormat.setCode(quizCode);
        for (int i = 0; i < unformattedQuiz.size(); i++) {
            Quiz current = unformattedQuiz.get(i);

            ChoiceResponseFormat choiceResponseFormat = new ChoiceResponseFormat();
            choiceResponseFormat.setOption(current.getQuestionChoice().getOption());
            choiceResponseFormat.setChoiceText(current.getQuestionChoice().getChoice().getText());
            questionResponseFormat.getChoices().add(choiceResponseFormat);

            // If at the end or next question is different, add the current question and its choice to quiz
            if (i == unformattedQuiz.size() - 1 || !current.getQuestionNumber().equals(unformattedQuiz.get(i + 1).getQuestionNumber())) {
                questionResponseFormat.setQuestionNumber(current.getQuestionNumber());
                questionResponseFormat.setQuestionText(current.getQuestionChoice().getQuestion().getText());
                quizFormat.getQuestions().add(questionResponseFormat);
                questionResponseFormat = new QuestionResponseFormat();
            }
        }
        return quizFormat;
    }

    @Override
    @Transactional
    public GetQuizResponse getQuizByCode(String quizEncode, String token) {
        GetQuizResponse response = new GetQuizResponse();
        Optional<QuizEncode> quizEncodeResult = quizEncodeRepository.findById(quizEncode);
        // Get quiz code from quiz encode
        if (quizEncodeResult.isEmpty()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.getErrors().add(new Error("not_found", "Quiz not found"));
            response.setSuccess(false);
            return response;
        }
        String quizCode = quizEncodeResult.get().getQuizCode().getCode();

        // Handle quiz code don't exist
        if (quizCodeRepository.findById(quizCode).isEmpty()) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.getErrors().add(new Error("not_found", "Quiz not found"));
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

        // Handle quiz code in token not matching requested quiz code
        String quizCodeToken = (String) authenticationService.getCustomClaim(token, "quizCode");
        if (!quizEncode.equals(quizCodeToken)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setSuccess(false);
            response.getErrors().add(new Error("unauthorized", "Quiz encode mismatch"));
            return response;
        }

        // Handle expiration date
        String groupName = (String) authenticationService.getCustomClaim(token, "groupName");
        Optional<Date> startTime = loginRepository.findStartTimeByNameAndQuizCode(groupName, quizEncode);
        if (startTime.isPresent()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setSuccess(false);
            response.getErrors().add(new Error("started", "Group already start the quiz"));
            return response;
        }

        // Retrieving quiz from database
        Sort sort = Sort.by("questionNumber").ascending().and(Sort.by("questionChoice.option"));
        Optional<List<Quiz>> quiz = quizRepository.getQuizByQuizCode(quizCode, sort);
        response.setQuiz(parseQuiz(quiz.get(), quizEncode));
        response.setSuccess(true);
        response.setStatus(HttpStatus.OK.value());

        // Update quiz start time for group and quiz
        Date date = new Date(System.currentTimeMillis());
        loginRepository.updateStartTime(groupName, quizCode, date);

        return response;
    }
}
