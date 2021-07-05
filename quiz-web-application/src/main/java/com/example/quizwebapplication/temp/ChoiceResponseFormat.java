package com.example.quizwebapplication.temp;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChoiceResponseFormat {
    private String option;
    private String choiceText;
    private boolean isCorrect;
}
