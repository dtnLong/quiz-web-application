package com.example.quizwebapplication.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionFormat {

    private Long questionNumber;
    private List<ChoiceFormat> choices = new ArrayList<>();

}
