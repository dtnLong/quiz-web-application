package com.example.quizwebapplication.repository;

import com.example.quizwebapplication.entity.QuestionChoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionChoiceRepository extends JpaRepository<QuestionChoice, Long> {
}