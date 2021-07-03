package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dto.SaveAnswerRequest;
import com.example.quizwebapplication.dto.SaveAnswerResponse;

public interface AnswerService {
    SaveAnswerResponse saveAnswers(SaveAnswerRequest providedAnswers);
}
