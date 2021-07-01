package com.example.quizwebapplication.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetQuizResponse {
    private Integer status;
    private List<Error> errors = new ArrayList<>();
    private boolean success = true;
    private QuizFormat quiz;
}
