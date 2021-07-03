package com.example.quizwebapplication.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionResponseFormat {
    private Long questionNumber;
    private List<ChoiceResponseFormat> choices = new ArrayList<>();
}
