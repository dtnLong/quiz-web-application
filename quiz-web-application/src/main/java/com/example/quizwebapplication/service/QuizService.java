package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dto.QuizFormat;
import com.example.quizwebapplication.entity.Quiz;

import java.util.List;

public interface QuizService {
    QuizFormat getQuizByCode(String quizCode, String groupName);
}
