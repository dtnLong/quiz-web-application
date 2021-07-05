package com.example.quizwebapplication.repository;

import com.example.quizwebapplication.entity.Choice;
import com.example.quizwebapplication.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    @Modifying
    @Query(value = "SELECT C FROM Choice C WHERE C.text = :choiceText")
    Question getQuestionByText(String choiceText);
}