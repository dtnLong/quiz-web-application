package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dto.SaveAnswerRequest;
import com.example.quizwebapplication.dto.SaveAnswerResponse;
import com.example.quizwebapplication.dto.MarkingResponse;

public interface AnswerService {
    String sendAnswerMail(SaveAnswerRequest providedAnswers);
    SaveAnswerResponse saveAnswers(SaveAnswerRequest providedAnswers, String token);
    MarkingResponse markAnswer(String groupName);
}
