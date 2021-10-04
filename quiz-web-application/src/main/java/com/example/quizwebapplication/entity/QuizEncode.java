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
@Table(name = "quiz_encode")
public class QuizEncode {
    @Id
    private String encode;

    @ManyToOne(targetEntity = QuizCode.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "quiz_code", referencedColumnName = "code")
    private QuizCode quizCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizEncode that = (QuizEncode) o;
        return Objects.equals(encode, that.encode) && Objects.equals(quizCode, that.quizCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(encode, quizCode);
    }
}
