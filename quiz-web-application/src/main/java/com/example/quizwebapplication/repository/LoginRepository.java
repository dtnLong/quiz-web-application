package com.example.quizwebapplication.repository;

import com.example.quizwebapplication.entity.Login;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    @Query(value = "SELECT L FROM Login L WHERE L.group.name = :groupName AND L.quizEncode.encode = :quizCode")
    Optional<Login> findByGroupNameAndQuizCode(String groupName, String quizCode);

    @Query(value = "SELECT L.startTime FROM Login L WHERE L.group.name = :groupName AND L.quizEncode.encode = :quizCode")
    Optional<Date> findStartTimeByNameAndQuizCode(String groupName, String quizCode);

    @Query(value = "SELECT L.submitted FROM Login L WHERE L.group.name = :groupName AND L.quizEncode.encode = :quizCode")
    Optional<Boolean> findSubmittedByNameAndQuizCode(String groupName, String quizCode);

    @Modifying
    @Query(value = "UPDATE Login L SET L.expired = :expired WHERE L IN (" +
            "SELECT L1 FROM Login L1 WHERE L1.group.name = :name AND L1.quizEncode.encode = :quizCode)")
    void updateExpired(String name, String quizCode, boolean expired);

    @Modifying
    @Query(value = "UPDATE Login L SET L.submitted = :submitted WHERE L IN (" +
            "SELECT L1 FROM Login L1 WHERE L1.group.name = :name AND L1.quizEncode.encode = :quizCode)")
    void updateSubmitted(String name, String quizCode, boolean submitted);

    @Modifying
    @Query(value = "UPDATE Login L SET L.startTime = :startTime WHERE L IN (" +
            "SELECT L1 FROM Login L1 WHERE L1.group.name = :name AND L1.quizEncode.encode = :quizCode)")
    void updateStartTime(String name, String quizCode, Date startTime);
}
