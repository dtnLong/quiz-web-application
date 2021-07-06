package com.example.quizwebapplication.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetQuizResponse extends Response {
    private QuizFormat quiz;
}
