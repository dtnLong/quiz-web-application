package com.example.quizwebapplication.controller;

import com.example.quizwebapplication.dto.Error;
import com.example.quizwebapplication.dto.GetQuizResponse;
import com.example.quizwebapplication.service.AuthenticationService;
import com.example.quizwebapplication.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
public class QuizController {

    private final AuthenticationService authenticationService;
    private final QuizService quizService;

    @GetMapping(value = "/api/quiz/{quizCode}")
    public ResponseEntity<GetQuizResponse> getQuiz(@CookieValue(value = "token", required = false) String token,
                                                 @PathVariable String quizCode) {
        GetQuizResponse response = new GetQuizResponse();
        if (token == null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setSuccess(false);
            response.getErrors().add(new Error("no token", "Unauthorized access"));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        if (!authenticationService.verifyToken(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setSuccess(false);
            response.getErrors().add(new Error("invalid", "Unauthorized access"));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);
        response.setQuiz(quizService.getQuizByCode(quizCode));
        return ResponseEntity.ok().body(response);
    }

}
