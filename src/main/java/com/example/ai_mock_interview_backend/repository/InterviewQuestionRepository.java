package com.example.ai_mock_interview_backend.repository;

import com.example.ai_mock_interview_backend.entity.InterviewQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InterviewQuestionRepository
        extends JpaRepository<InterviewQuestion, Long> {

    List<InterviewQuestion>
    findByInterviewSession_SessionIdOrderByQuestionIdAsc(Long sessionId);
}