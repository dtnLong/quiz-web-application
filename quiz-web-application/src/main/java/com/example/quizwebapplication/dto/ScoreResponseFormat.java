package com.example.quizwebapplication.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScoreResponseFormat {
    private Long questionNumber;
    private String providedAnswer;
    private String correctAnswer;
    private boolean isCorrect;
}


