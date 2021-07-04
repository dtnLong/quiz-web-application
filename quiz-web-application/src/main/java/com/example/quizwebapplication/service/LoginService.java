package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dto.LoginResponse;

import java.util.Date;

public interface LoginService {
    void updateStartTime(String groupName, String quizCode);
    boolean getSubmitted(String groupName, String quizCode);
    Date getStartTime(String groupName, String quizCode);
    LoginResponse checkLogin(String groupName, String quizCode);
}
