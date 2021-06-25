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
        Optional<Login> savedLogin = loginRepository.findByGroupName(groupName);
        LoginResponse response = new LoginResponse();
        if (savedLogin.isEmpty()) {
            response.getErrors().add(new Error("name not found", "Group name not found"));
            response.setSuccess(false);
            return response;
        }
        if (savedLogin.get().isExpired()) {
            response.getErrors().add(new Error("expired", "Login credential expired"));
            response.setSuccess(false);
            return response;
        }
        if (!savedLogin.get().getQuizCode().getCode().equals(quizCode)) {
            response.getErrors().add(new Error("incorrect quiz code", "Quiz code not found for group name"));
            response.setSuccess(false);
            return response;
        }
        loginRepository.updateExpired(groupName, true);
        return response;
    }
}
