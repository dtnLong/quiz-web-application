package com.example.quizwebapplication.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "quiz_date")
public class QuizDate {

    @Id
    private String dateCode;

    @Column
    LocalDateTime datetime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuizDate quizDate = (QuizDate) o;
        return Objects.equals(dateCode, quizDate.dateCode) && Objects.equals(datetime, quizDate.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateCode, datetime);
    }
}
