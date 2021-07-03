package com.example.quizwebapplication.repository;

import com.example.quizwebapplication.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query(value = "SELECT G FROM Group G WHERE G.name = :name")
    Optional<Group> getGroupByName(String name);

    @Modifying
    @Query(value = "UPDATE Group G SET G.attempt = G.attempt + 1 WHERE G IN (" +
            "SELECT G1 FROM Group G1 WHERE G1.name = :name)")
    void addAttempt(String name);

    @Modifying
    @Query(value = "UPDATE Group G SET G.quizCode.code = :quizCode WHERE G.name = :name")
    void updateLatestQuiz(String quizCode, String name);
}
