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
@Table(name = "group_register")
public class Group {

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private Short attempt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) && Objects.equals(name, group.name) && Objects.equals(attempt, group.attempt) && Objects.equals(score, group.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, attempt, score);
    }

    @Column
    private Long score;

}
