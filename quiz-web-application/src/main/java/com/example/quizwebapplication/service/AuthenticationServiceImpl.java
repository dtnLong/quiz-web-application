package com.example.quizwebapplication.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private String secretKey = "G+KbPdSgVkYp3s6v9y$B&E)H@McQfThW";

    @Override
    public Object getCustomClaim(String token, String claimKey) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.get(claimKey);
    }

    @Override
    public String createToken(Map<String, Object> payload) {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setClaims(payload)
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    @Override
    public boolean verifyToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
