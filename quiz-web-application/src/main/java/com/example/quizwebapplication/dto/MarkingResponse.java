package com.example.quizwebapplication.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarkingResponse extends Response {
    private Long score;
    private String quizCode;
    private String groupName;
    private List<ScoreResponseFormat> answerList = new ArrayList<>();
}
