package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dto.ChoiceFormat;
import com.example.quizwebapplication.dto.QuestionFormat;
import com.example.quizwebapplication.dto.QuizFormat;
import com.example.quizwebapplication.entity.Quiz;
import com.example.quizwebapplication.repository.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    private QuizFormat parseQuiz(List<Quiz> unformattedQuiz, String quizCode) {
        QuizFormat quizFormat = new QuizFormat();
        QuestionFormat questionFormat = new QuestionFormat();
        quizFormat.setCode(quizCode);
        for (int i = 0; i < unformattedQuiz.size(); i++) {
            Quiz current = unformattedQuiz.get(i);

            ChoiceFormat choiceFormat = new ChoiceFormat();
            choiceFormat.setOption(current.getQuestionChoice().getOption());
            choiceFormat.setChoiceText(current.getQuestionChoice().getChoice().getText());
            questionFormat.getChoices().add(choiceFormat);

            if (i == unformattedQuiz.size() - 1 || !current.getQuestionNumber().equals(unformattedQuiz.get(i + 1).getQuestionNumber())) {
                questionFormat.setQuestionNumber(current.getQuestionNumber());
                quizFormat.getQuestions().add(questionFormat);
                questionFormat = new QuestionFormat();
            }
        }
        return quizFormat;
    }

    @Override
    @Transactional
    public QuizFormat getQuizByCode(String quizCode) {
        Sort sort = Sort.by("questionNumber").ascending();
        Optional<List<Quiz>> quiz = quizRepository.getQuizByQuizCode(quizCode, sort);
        if (quiz.isEmpty()) {
            return null;
        }
        return parseQuiz(quiz.get(), quizCode);
    }
}
