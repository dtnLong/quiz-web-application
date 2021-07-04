package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dto.Error;
import com.example.quizwebapplication.dto.LoginResponse;
import com.example.quizwebapplication.entity.Login;
import com.example.quizwebapplication.repository.LoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Override
    @Transactional
    public Date getStartTime(String groupName, String quizCode) {
        Optional<Date> startTime = loginRepository.findStartTimeByNameAndQuizCode(groupName, quizCode);
        if (startTime.isEmpty()) {
            return null;
        }
        return startTime.get();
    }

    @Override
    @Transactional
    public boolean getSubmitted(String groupName, String quizCode) {
        return loginRepository.findSubmittedByNameAndQuizCode(groupName, quizCode).get();
    }

    @Override
    @Transactional
    public void updateStartTime(String groupName, String quizCode) {
        Date date = new Date(System.currentTimeMillis());
        loginRepository.updateStartTime(groupName, quizCode, date);
    }

    @Override
    @Transactional
    public LoginResponse checkLogin(String groupName, String quizCode) {
        Optional<Login> savedLogin = loginRepository.findByGroupNameAndQuizCode(groupName, quizCode);
        LoginResponse response = new LoginResponse();
        if (savedLogin.isEmpty()) {
            response.getErrors().add(new Error("invalid", "Group name not exist or Quiz code invalid"));
            response.setSuccess(false);
            return response;
        }
        if (savedLogin.get().isExpired()) {
            response.getErrors().add(new Error("expired", "Login credential expired"));
            response.setSuccess(false);
            return response;
        }
        System.out.println("Update Expired");
        loginRepository.updateExpired(groupName, quizCode,true);
        return response;
    }
}
