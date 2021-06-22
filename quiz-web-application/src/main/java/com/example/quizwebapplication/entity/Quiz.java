package com.example.quizwebapplication.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "quiz_question_choice")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = QuizCode.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "quiz_code", referencedColumnName = "code")
    private QuizCode quizCode;

    @Column
    private Long questionNumber;

    @ManyToOne(targetEntity = QuestionChoice.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "question_choice_id", referencedColumnName = "id")
    private QuestionChoice questionChoice;
}
