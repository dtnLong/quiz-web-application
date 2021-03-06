package com.example.quizwebapplication.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizFormat {
    private String code;
    private List<QuestionResponseFormat> questions = new ArrayList<>();
}
