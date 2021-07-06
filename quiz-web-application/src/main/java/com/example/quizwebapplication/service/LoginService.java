package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dto.LoginResponse;

public interface LoginService {
    LoginResponse checkLogin(String groupName, String quizCode);
}
