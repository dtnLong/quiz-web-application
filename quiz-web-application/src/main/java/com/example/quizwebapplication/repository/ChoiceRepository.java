package com.example.quizwebapplication.repository;

import com.example.quizwebapplication.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
}