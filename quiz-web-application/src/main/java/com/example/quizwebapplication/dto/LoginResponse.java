package com.example.quizwebapplication.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginResponse {
    private Integer status;
    private ArrayList<Error> errors = new ArrayList<>();
    private boolean success = true;
}
