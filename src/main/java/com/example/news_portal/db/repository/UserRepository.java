package com.example.news_portal.db.repository;

import com.example.news_portal.db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.nickname = :name")
    Optional<User> findByName(String name);

}