package com.example.orm_np1_lab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SlowUserRepository extends JpaRepository<SlowUser, Long> {
}

