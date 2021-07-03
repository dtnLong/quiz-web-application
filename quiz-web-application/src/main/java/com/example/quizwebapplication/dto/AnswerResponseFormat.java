package com.example.quizwebapplication.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnswerResponseFormat {
    private Long questionNumber;
    private String answer;
}
