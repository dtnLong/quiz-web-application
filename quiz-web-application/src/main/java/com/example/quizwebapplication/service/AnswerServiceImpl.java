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

    @Transactional
    @Override
    public SaveAnswerResponse saveAnswers(SaveAnswerRequest providedAnswers) {
        SaveAnswerResponse response = new SaveAnswerResponse();
        QuizCode quizCode = quizCodeRepository.getById(providedAnswers.getQuizCode());
        Optional<Group> group = groupRepository.getGroupByName(providedAnswers.getGroupName());

        // Check if group and quiz code is available
        if (group.isEmpty()) {
            response.getErrors().add(new Error("not_found", "Group not found"));
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setSuccess(false);
            return response;
        }

        if (quizCode == null) {
            response.getErrors().add(new Error("not_found", "Quiz code not found"));
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.setSuccess(false);
            return response;
        }

        // Save answers to database
        for (int i = 0; i < providedAnswers.getAnswers().size(); i++) {
            Answer answer = new Answer();
            answer.setQuizCode(quizCode);
            answer.setGroup(group.get());
            answer.setAnswerChoice(providedAnswers.getAnswers().get(i).getAnswer());
            answer.setQuestionNumber(providedAnswers.getAnswers().get(i).getQuestionNumber());
            answerRepository.save(answer);
        }

        // Increase attempt of the submit group
        groupRepository.addAttempt(providedAnswers.getGroupName());
        groupRepository.updateLatestQuiz(providedAnswers.getQuizCode(), providedAnswers.getGroupName());
        loginRepository.updateSubmitted(providedAnswers.getGroupName(), providedAnswers.getQuizCode(), true);
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        return response;
    }

}
