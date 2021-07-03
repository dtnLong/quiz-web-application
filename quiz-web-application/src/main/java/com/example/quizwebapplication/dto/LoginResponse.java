package com.example.quizwebapplication.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponse extends Response {
    private String token;
}
