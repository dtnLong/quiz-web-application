package com.example.quizwebapplication.temp;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetQuizResponse extends Response {
    private QuizFormat quiz;
}
