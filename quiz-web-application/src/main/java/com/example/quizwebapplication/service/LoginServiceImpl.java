package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dto.Error;
import com.example.quizwebapplication.dto.LoginResponse;
import com.example.quizwebapplication.entity.Login;
import com.example.quizwebapplication.repository.LoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;

    @Override
    @Transactional
    public LoginResponse checkLogin(String groupName, String quizCode) {
        Optional<Login> savedLogin = loginRepository.findByGroupNameAndQuizCode(groupName, quizCode);
        LoginResponse response = new LoginResponse();

        if (savedLogin.isEmpty()) {
            response.getErrors().add(new Error("invalid", "Group name not exists or Quiz code is invalid"));
            response.setSuccess(false);
            return response;
        }
        if (savedLogin.get().isExpired()) {
            response.getErrors().add(new Error("expired", "You have done the quiz. Excessive attempts!"));
            response.setSuccess(false);
            return response;
        }

        // Set the login credential to expired after 1 login
        loginRepository.updateExpired(groupName, quizCode,true);
        return response;
    }
}
