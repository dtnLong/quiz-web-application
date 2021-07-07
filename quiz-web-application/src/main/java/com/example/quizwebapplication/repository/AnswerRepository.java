package com.example.quizwebapplication.repository;

import com.example.quizwebapplication.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query(value = "SELECT A FROM Answer A WHERE A.group.name = :groupName AND A.quizCode.code = :quizCode")
    Optional<List<Answer>> findAnswerByGroupNameAndQuizCode(String groupName, String quizCode);
}
