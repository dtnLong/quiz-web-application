package com.example.quizwebapplication.service;

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
    public String checkLogin(String groupName, String quizCode) {
        Optional<Login> savedLogin = loginRepository.findByGroupName(groupName);
        if (savedLogin.isEmpty()) {
            return "not found";
        }
        if (savedLogin.get().isExpired()) {
            return "expired";
        }
        if (!savedLogin.get().getQuizCode().getCode().equals(quizCode)) {
            return "code";
        }
        loginRepository.updateExpired(groupName, true);
        return "success";
    }
}
