package com.example.quizwebapplication.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Group.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "group_id", referencedColumnName = "id")
    private Group group;

    @OneToOne(targetEntity = QuizCode.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "quiz_code", referencedColumnName = "code")
    private QuizCode quizCode;

    @Column
    private Long questionNumber;

    @Column
    private String answerChoice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(id, answer.id) && Objects.equals(group, answer.group) && Objects.equals(quizCode, answer.quizCode) && Objects.equals(questionNumber, answer.questionNumber) && Objects.equals(answerChoice, answer.answerChoice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, group, quizCode, questionNumber, answerChoice);
    }
}
