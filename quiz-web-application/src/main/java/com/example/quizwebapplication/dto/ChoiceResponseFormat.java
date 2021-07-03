package com.example.quizwebapplication.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChoiceResponseFormat {
    private String option;
    private String choiceText;
}
