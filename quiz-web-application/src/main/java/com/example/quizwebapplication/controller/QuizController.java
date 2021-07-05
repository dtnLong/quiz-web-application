package com.example.quizwebapplication.controller;

import com.example.quizwebapplication.dto.Error;
import com.example.quizwebapplication.dto.GetQuizResponse;
import com.example.quizwebapplication.dto.SaveAnswerRequest;
import com.example.quizwebapplication.dto.SaveAnswerResponse;
import com.example.quizwebapplication.service.AnswerService;
import com.example.quizwebapplication.service.AuthenticationService;
import com.example.quizwebapplication.service.LoginService;
import com.example.quizwebapplication.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@AllArgsConstructor
public class QuizController {

    private final AuthenticationService authenticationService;
    private final QuizService quizService;
    private final AnswerService answerService;
    private final LoginService loginService;

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

        String quizCodeToken = (String) authenticationService.getCustomClaim(token, "quizCode");
        String groupNameToken = (String) authenticationService.getCustomClaim(token, "groupName");
        if (!quizCode.equals(quizCodeToken)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setSuccess(false);
            response.getErrors().add(new Error("invalid", "Unauthorized access"));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Set-Cookie", "token=null; Path=/api; Max-Age=0; HttpOnly");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).body(response);
        }

        if (loginService.getStartTime(groupNameToken, quizCode) != null) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setSuccess(false);
            response.getErrors().add(new Error("quiz_started", "Quiz already started"));
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        response.setQuiz(quizService.getQuizByCode(quizCode, groupNameToken));
        loginService.updateStartTime(groupNameToken, quizCode);

        response.setStatus(HttpStatus.OK.value());
        response.setSuccess(true);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping(value = "/api/quiz/answer")
    public ResponseEntity<SaveAnswerResponse> saveAnswer(@CookieValue(value = "token", required = false) String token,
                                                         @RequestBody SaveAnswerRequest answers) {
        SaveAnswerResponse response = new SaveAnswerResponse();

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

        String quizCodeToken = (String) authenticationService.getCustomClaim(token, "quizCode");
        String groupNameToken = (String) authenticationService.getCustomClaim(token, "groupName");
        if (!answers.getQuizCode().equals(quizCodeToken) || !answers.getGroupName().equals(groupNameToken)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setSuccess(false);
            response.getErrors().add(new Error("invalid", "Unauthorized access"));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Set-Cookie", "token=null; Path=/api; Max-Age=0; HttpOnly");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).body(response);
        }

        if (loginService.getSubmitted(answers.getGroupName(), answers.getQuizCode())) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setSuccess(false);
            response.getErrors().add(new Error("invalid", "Unauthorized access. Answer already submitted"));

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.add("Set-Cookie", "token=null; Path=/api; Max-Age=0; HttpOnly");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(responseHeaders).body(response);
        }

        response = answerService.saveAnswers(answers);
        if (!response.isSuccess()) {
            return ResponseEntity.status(response.getStatus()).body(response);
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Set-Cookie", "token=null; Path=/api; Max-Age=0; HttpOnly");

        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

}
