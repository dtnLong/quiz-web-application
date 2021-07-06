package com.example.quizwebapplication.controller;

import com.example.quizwebapplication.dto.GetQuizResponse;
import com.example.quizwebapplication.dto.SaveAnswerRequest;
import com.example.quizwebapplication.dto.SaveAnswerResponse;
import com.example.quizwebapplication.service.AnswerService;
import com.example.quizwebapplication.service.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@AllArgsConstructor
public class QuizController {
    private final QuizService quizService;
    private final AnswerService answerService;

    @GetMapping(value = "/api/quiz/{quizCode}")
    public ResponseEntity<GetQuizResponse> getQuiz(@CookieValue(value = "token", required = false) String token,
                                                 @PathVariable String quizCode) {
        GetQuizResponse response = quizService.getQuizByCode(quizCode, token);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping(value = "/api/quiz/answer")
    public ResponseEntity<SaveAnswerResponse> saveAnswer(@CookieValue(value = "token", required = false) String token,
                                                         @RequestBody SaveAnswerRequest answers) {
        SaveAnswerResponse response = answerService.saveAnswers(answers, token);
        if (!response.isSuccess()) {
            return ResponseEntity.status(response.getStatus()).body(response);
        }

        // When success, delete jwt
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Set-Cookie", "token=null; Path=/api; Max-Age=0; HttpOnly");
        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

}
