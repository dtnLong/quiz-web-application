package com.example.quizwebapplication.temp;

import com.example.quizwebapplication.entity.*;
import com.example.quizwebapplication.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@AllArgsConstructor
public class TempController {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final ChoiceRepository choiceRepository;
    private final QuestionChoiceRepository questionChoiceRepository;
    private final QuizCodeRepository quizCodeRepository;
    private final QuizServiceImplTemp quizService;

    @PostMapping(value = "api/temp/quiz")
    @Transactional
    public GetQuizResponse addQuiz(@RequestBody QuizFormat quiz) {
        GetQuizResponse response = new GetQuizResponse();
        if (!quizCodeRepository.findById(quiz.getCode()).isEmpty()) {
            response.getErrors().add(new Error("quiz code already exist", "quiz code already exist"));
            return response;
        }
        QuizCode quizCode = quizCodeRepository.save(new QuizCode(quiz.getCode()));

        List<Question> storedQuestionList = questionRepository.findAll();
        List<Choice> storedChoiceList = choiceRepository.findAll();
        List<QuestionChoice> questionChoiceList = questionChoiceRepository.findAll();

        Map<String, Question> storedQuestionMap = new HashMap<>();
        Map<String, Choice> storedChoiceMap = new HashMap<>();

        // Map Question and Choice object to their text for faster access
        for (Question question:storedQuestionList) {
            storedQuestionMap.put(question.getText(), question);
        }

        for (Choice choice:storedChoiceList) {
            storedChoiceMap.put(choice.getText(), choice);
        }

        for (QuestionResponseFormat question:quiz.getQuestions()) {
            // If question don't exist add new and add it to the map to retrieve
            if (storedQuestionMap.get(question.getQuestionText()) == null) {
                Question newQuestion = new Question();
                newQuestion.setText(question.getQuestionText());
                newQuestion = questionRepository.saveAndFlush(newQuestion);
                storedQuestionMap.put(question.getQuestionText(), newQuestion);
            }
            Question currentQuestion = storedQuestionMap.get(question.getQuestionText());

            for (ChoiceResponseFormat choice:question.getChoices()) {
                // If choice don't exist add new and add it to the map to retrieve
                if (storedChoiceMap.get(choice.getChoiceText()) == null) {
                    Choice newChoice = new Choice();
                    newChoice.setText(choice.getChoiceText());
                    newChoice = choiceRepository.saveAndFlush(newChoice);
                    storedChoiceMap.put(choice.getChoiceText(), newChoice);
                }
                Choice currentChoice = storedChoiceMap.get(choice.getChoiceText());

                QuestionChoice currentQuestionChoice = new QuestionChoice();
                if (questionChoiceList.isEmpty()) {
                    currentQuestionChoice.setChoice(currentChoice);
                    currentQuestionChoice.setQuestion(currentQuestion);
                    currentQuestionChoice.setCorrect(choice.isCorrect());
                    currentQuestionChoice.setOption(choice.getOption());
                    currentQuestionChoice = questionChoiceRepository.saveAndFlush(currentQuestionChoice);
                    questionChoiceList.add(currentQuestionChoice);
                } else {
                    // Add Question Choice if not exist
                    for (int i = 0; i < questionChoiceList.size(); i++) {
                        QuestionChoice questionChoice = questionChoiceList.get(i);
                        // If question choice already exist use it
                        if (currentChoice.equals(questionChoice.getChoice())
                                && currentQuestion.equals(questionChoice.getQuestion())
                                && choice.isCorrect() == questionChoice.isCorrect()
                                && choice.getOption().equals(questionChoice.getOption())) {
                            currentQuestionChoice = questionChoice;
                            break;
                        }
                        // Create new question_choice if not exist then add to the list
                        if (i == questionChoiceList.size() - 1) {
                            currentQuestionChoice.setChoice(currentChoice);
                            currentQuestionChoice.setQuestion(currentQuestion);
                            currentQuestionChoice.setCorrect(choice.isCorrect());
                            currentQuestionChoice.setOption(choice.getOption());
                            currentQuestionChoice = questionChoiceRepository.saveAndFlush(currentQuestionChoice);
                            questionChoiceList.add(currentQuestionChoice);
                            break;
                        }
                    }
                }

                // Add quiz to database
                Quiz newQuiz = new Quiz();
                newQuiz.setQuizCode(quizCode);
                newQuiz.setQuestionChoice(currentQuestionChoice);
                newQuiz.setQuestionNumber(question.getQuestionNumber());
                quizRepository.save(newQuiz);
            }
        }
        response.setQuiz(quizService.getQuizByCode(quiz.getCode(), "a"));
        return response;
    }
}
