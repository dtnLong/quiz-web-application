package com.example.quizwebapplication.service;

import com.example.quizwebapplication.dto.Error;
import com.example.quizwebapplication.dto.LoginResponse;
import com.example.quizwebapplication.entity.Login;
import com.example.quizwebapplication.repository.LoginRepository;
import com.example.quizwebapplication.repository.QuizDateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginRepository;
    private final QuizDateRepository quizDateRepository;

    @Override
    @Transactional
    public LoginResponse checkLogin(String groupName, String quizCode) {
        Optional<Login> savedLogin = loginRepository.findByGroupNameAndQuizCode(groupName, quizCode);
        LoginResponse response = new LoginResponse();

        // Check time
        Instant instant = Instant.now();
        LocalDateTime currentTime = LocalDateTime.ofInstant(instant, ZoneOffset.ofHours(7));
        LocalDateTime openDateTime = quizDateRepository.findById("quizStart").get().getDatetime();
        LocalDateTime closeDateTime = quizDateRepository.findById("quizEnd").get().getDatetime();
        if (currentTime.isBefore(openDateTime) || currentTime.isAfter(closeDateTime)) {
            response.getErrors().add(new Error("closed", "Quiz is closed"));
            response.setSuccess(false);
            return response;
        }

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
