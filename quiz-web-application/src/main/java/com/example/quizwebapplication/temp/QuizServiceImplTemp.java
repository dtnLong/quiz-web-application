package com.example.quizwebapplication.temp;

import com.example.quizwebapplication.entity.Quiz;
import com.example.quizwebapplication.repository.LoginRepository;
import com.example.quizwebapplication.repository.QuizRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class QuizServiceImplTemp {

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
            choiceResponseFormat.setCorrect(current.getQuestionChoice().isCorrect());
            questionResponseFormat.getChoices().add(choiceResponseFormat);

            if (i == unformattedQuiz.size() - 1 || !current.getQuestionNumber().equals(unformattedQuiz.get(i + 1).getQuestionNumber())) {
                questionResponseFormat.setQuestionNumber(current.getQuestionNumber());
                questionResponseFormat.setQuestionText(current.getQuestionChoice().getQuestion().getText());
                quizFormat.getQuestions().add(questionResponseFormat);
                questionResponseFormat = new QuestionResponseFormat();
            }
        }
        return quizFormat;
    }

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
