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
@Table(name = "group_registered")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "leader_email")
    private String leaderEmail;

    @Column
    private Short attempt;

    @Column
    private Long score;

    @OneToOne(targetEntity = QuizCode.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "latest_quiz", referencedColumnName = "code", nullable = true)
    private QuizCode quizCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id)
                && Objects.equals(name, group.name)
                && Objects.equals(attempt, group.attempt)
                && Objects.equals(score, group.score)
                && Objects.equals(quizCode, group.quizCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, attempt, score, quizCode);
    }
}
