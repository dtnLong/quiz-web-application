package com.example.quizwebapplication.controller;

import com.example.quizwebapplication.service.LoginService;
import lombok.AllArgsConstructor;
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

    @PostMapping(value = "/api/login")
    public ResponseEntity<Map<String, String>> loginGroup(@RequestBody Map<String, String> loginInfo) {
        Map<String, String> responseMessage = new HashMap<>();
        String loginStatus = loginService.checkLogin(loginInfo.get("groupName"), loginInfo.get("quizCode"));
        responseMessage.put("status", loginStatus);
        if (!loginStatus.equals("success")) {
            return new ResponseEntity<>(responseMessage, HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

}
