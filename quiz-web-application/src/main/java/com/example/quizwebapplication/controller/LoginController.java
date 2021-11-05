package com.example.quizwebapplication.controller;

import com.example.quizwebapplication.dto.LoginResponse;
import com.example.quizwebapplication.service.AuthenticationService;
import com.example.quizwebapplication.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "http://quiz.thelogisticom.org", allowCredentials = "true")
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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // Create jwt contain group name and quiz code
        String token = authenticationService.createToken(loginInfo);
        HttpHeaders responseHeaders = new HttpHeaders();
        // Add token to http only cookie with max age of 2h30min
        responseHeaders.add("Set-Cookie", "token=" + token + "; Domain=thelogisticom.org; Path=/api; Max-Age=9000; HttpOnly; ");
        response.setStatus(HttpStatus.OK.value());

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

}
