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

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/api/login")
    public ResponseEntity<LoginResponse> loginGroup(@RequestBody Map<String, Object> loginInfo, HttpServletRequest request) {
        LoginResponse response = loginService.checkLogin((String) loginInfo.get("groupName"), (String) loginInfo.get("quizCode"));
        if (!response.isSuccess()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        String token = authenticationService.createToken(loginInfo);
        response.setToken(token);
        response.setStatus(HttpStatus.OK.value());

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Set-Cookie", "token=" + token + "; Path=/api; Max-Age=172800; HttpOnly");
        System.out.println(request.getHeader("origin").split("//")[1]);
        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }

}
