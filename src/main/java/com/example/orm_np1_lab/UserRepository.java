package com.example.orm_np1_lab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.posts")
    List<User> findAllWithPosts();

    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.subordinates WHERE u.depth = :depth")
    List<User> findAllWithSubsByDepth(Integer depth);
}

