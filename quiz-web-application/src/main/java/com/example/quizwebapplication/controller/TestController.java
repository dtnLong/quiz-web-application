package com.example.quizwebapplication.controller;

import com.example.quizwebapplication.dto.LoginResponse;
import com.example.quizwebapplication.entity.Login;
import com.example.quizwebapplication.service.AuthenticationService;
import com.example.quizwebapplication.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class TestController {

    private final AuthenticationService authenticationService;

    @GetMapping(value = "/api/test/name")
    public ResponseEntity<Map<String, String>> loginGroup(@CookieValue("token") String token) {
        Map<String, String> response = new HashMap<>();
        response.put("cookieToken", token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
