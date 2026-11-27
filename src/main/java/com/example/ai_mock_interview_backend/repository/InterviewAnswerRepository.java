package com.example.ai_mock_interview_backend.repository;

import com.example.ai_mock_interview_backend.entity.InterviewAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InterviewAnswerRepository
        extends JpaRepository<InterviewAnswer, Long> {

    List<InterviewAnswer>
    findByInterviewSession_SessionId(Long sessionId);

    List<InterviewAnswer>
    findByInterviewQuestion_QuestionId(Long questionId);
}
