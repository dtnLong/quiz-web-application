package com.example.quizwebapplication.repository;

import com.example.quizwebapplication.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    @Query(value = "SELECT L FROM Login L WHERE L.group.name = :groupName")
    Optional<Login> findByGroupName(String groupName);

    @Modifying
    @Query(value = "UPDATE Login L SET L.expired = :expired WHERE L IN (" +
            "SELECT L1 FROM Login L1 WHERE L1.group.name = :name)")
    void updateExpired(String name, boolean expired);
}
