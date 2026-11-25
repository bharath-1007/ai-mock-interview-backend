package com.example.ai_mock_interview_backend.repository;

import com.example.ai_mock_interview_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
}
