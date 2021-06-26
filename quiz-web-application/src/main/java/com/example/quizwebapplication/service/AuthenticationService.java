package com.example.quizwebapplication.service;

import java.util.Map;

public interface AuthenticationService {
    Object getCustomClaim(String token, String claimKey);
    String createToken(Map<String, Object> payload);
    boolean verifyToken(String token);
}
