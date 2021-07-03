package com.example.quizwebapplication.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SaveAnswerRequest {
    private String groupName;
    private String quizCode;
    private List<AnswerResponseFormat> answers = new ArrayList<>();
}
