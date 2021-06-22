package com.example.quizwebapplication.repository;

import com.example.quizwebapplication.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    @Query(value = "SELECT L FROM Login L WHERE L.group.name = :groupName")
    public Optional<Login> findByGroupName(String groupName);
}
