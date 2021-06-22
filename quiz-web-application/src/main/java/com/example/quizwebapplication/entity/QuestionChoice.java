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
@Table(name = "question_choice")
public class QuestionChoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Question.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "question_id", referencedColumnName = "id")
    private Question question;

    @Column
    private String option;

    @ManyToOne(targetEntity = Choice.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "choice_id", referencedColumnName = "id")
    private Choice choice;

    @Column
    private boolean isCorrect;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionChoice that = (QuestionChoice) o;
        return isCorrect == that.isCorrect &&
                Objects.equals(id, that.id) &&
                Objects.equals(question, that.question) &&
                Objects.equals(option, that.option) &&
                Objects.equals(choice, that.choice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, question, option, choice, isCorrect);
    }
}
