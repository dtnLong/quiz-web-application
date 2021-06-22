package com.example.quizwebapplication.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "quiz_code")
public class QuizCode {

    @Id
    private Long code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizCode quiz = (QuizCode) o;
        return Objects.equals(code, quiz.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
