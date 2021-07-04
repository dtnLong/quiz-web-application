package com.example.quizwebapplication.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Group.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "group_id", referencedColumnName = "id")
    private Group group;

    @ManyToOne(targetEntity = QuizCode.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "quiz_code", referencedColumnName = "code")
    private QuizCode quizCode;

    @Column
    private boolean expired = false;

    @Column
    private boolean submitted = false;

    @Column(name = "start_time")
    private Date startTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login = (Login) o;
        return expired == login.expired && Objects.equals(id, login.id) && Objects.equals(group, login.group) && Objects.equals(quizCode, login.quizCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, group, quizCode, expired);
    }
}
