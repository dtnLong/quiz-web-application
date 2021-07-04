package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dto.ChoiceResponseFormat;
import com.example.quizwebapplication.dto.QuestionResponseFormat;
import com.example.quizwebapplication.dto.QuizFormat;
import com.example.quizwebapplication.entity.Quiz;
import com.example.quizwebapplication.repository.LoginRepository;
import com.example.quizwebapplication.repository.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final LoginRepository loginRepository;

    private QuizFormat parseQuiz(List<Quiz> unformattedQuiz, String quizCode) {
        QuizFormat quizFormat = new QuizFormat();
        QuestionResponseFormat questionResponseFormat = new QuestionResponseFormat();
        quizFormat.setCode(quizCode);
        for (int i = 0; i < unformattedQuiz.size(); i++) {
            Quiz current = unformattedQuiz.get(i);

            ChoiceResponseFormat choiceResponseFormat = new ChoiceResponseFormat();
            choiceResponseFormat.setOption(current.getQuestionChoice().getOption());
            choiceResponseFormat.setChoiceText(current.getQuestionChoice().getChoice().getText());
            questionResponseFormat.getChoices().add(choiceResponseFormat);

            if (i == unformattedQuiz.size() - 1 || !current.getQuestionNumber().equals(unformattedQuiz.get(i + 1).getQuestionNumber())) {
                questionResponseFormat.setQuestionNumber(current.getQuestionNumber());
                quizFormat.getQuestions().add(questionResponseFormat);
                questionResponseFormat = new QuestionResponseFormat();
            }
        }
        return quizFormat;
    }

    @Override
    @Transactional
    public QuizFormat getQuizByCode(String quizCode, String groupName) {
        Sort sort = Sort.by("questionNumber").ascending();
        Optional<List<Quiz>> quiz = quizRepository.getQuizByQuizCode(quizCode, sort);
        if (quiz.isEmpty()) {
            return null;
        }
        QuizFormat quizResponse = parseQuiz(quiz.get(), quizCode);
        return quizResponse;
    }
}
