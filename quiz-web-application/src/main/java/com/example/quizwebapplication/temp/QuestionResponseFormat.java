package com.example.quizwebapplication.temp;

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
    private String questionText;
    private List<ChoiceResponseFormat> choices = new ArrayList<>();
}
