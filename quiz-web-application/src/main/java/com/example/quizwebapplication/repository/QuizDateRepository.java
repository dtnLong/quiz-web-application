package com.example.quizwebapplication.repository;

import com.example.quizwebapplication.entity.QuizDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDateRepository extends JpaRepository<QuizDate, String> {

}
