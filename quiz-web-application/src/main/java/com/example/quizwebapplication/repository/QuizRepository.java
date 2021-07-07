package com.example.quizwebapplication.repository;

import com.example.quizwebapplication.entity.Quiz;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query(value = "SELECT Q FROM Quiz Q WHERE Q.quizCode.code = :quizCode")
    Optional<List<Quiz>> getQuizByQuizCode(String quizCode, Sort sort);

    @Query(value = "SELECT Q FROM Quiz Q WHERE Q.quizCode.code = :quizCode AND Q.questionChoice.isCorrect = true")
    Optional<List<Quiz>> getCorrectAnswerByQuizCode(String quizCode, Sort sort);
}
