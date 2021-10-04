package com.example.quizwebapplication.repository;

import com.example.quizwebapplication.entity.QuizEncode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizEncodeRepository extends JpaRepository<QuizEncode, String> {

}
