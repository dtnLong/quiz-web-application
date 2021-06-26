package com.example.quizwebapplication.controller;

import com.example.quizwebapplication.dto.LoginResponse;
import com.example.quizwebapplication.entity.Login;
import com.example.quizwebapplication.service.AuthenticationService;
import com.example.quizwebapplication.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/api/login")
    public ResponseEntity<LoginResponse> loginGroup(@RequestBody Map<String, Object> loginInfo) {
        LoginResponse response = loginService.checkLogin((String) loginInfo.get("groupName"), (String) loginInfo.get("quizCode"));
        if (!response.isSuccess()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        response.setToken(authenticationService.createToken(loginInfo));
        response.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
