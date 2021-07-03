package com.example.quizwebapplication.repository;

import com.example.quizwebapplication.entity.QuizCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCodeRepository extends JpaRepository<QuizCode, String> {

}
