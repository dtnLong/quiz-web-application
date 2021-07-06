package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dto.GetQuizResponse;

public interface QuizService {
    GetQuizResponse getQuizByCode(String quizCode, String token);
}
