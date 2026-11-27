package com.example.ai_mock_interview_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ai_mock_interview_backend.entity.InterviewSession;

import java.util.List;

public interface InterviewSessionRepository extends JpaRepository<InterviewSession, Long> {

    // Get all interview sessions of a particular user
    List<InterviewSession> findByUserUserId(Long userId);

    // Get interview sessions based on status
    List<InterviewSession> findByStatus(String status);

    // Get interview sessions of a user based on interview type
    List<InterviewSession> findByUserUserIdAndInterviewType(Long userId, String interviewType);
}
