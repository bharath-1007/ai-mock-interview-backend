package com.example.ai_mock_interview_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ai_mock_interview_backend.entity.InterviewSession;
import com.example.ai_mock_interview_backend.repository.InterviewSessionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewSessionService {

    @Autowired
    private InterviewSessionRepository interviewSessionRepository;

    // Create Interview Session
    public InterviewSession createSession(InterviewSession session) {
        return interviewSessionRepository.save(session);
    }

    // Get All Interview Sessions
    public List<InterviewSession> getAllSessions() {
        return interviewSessionRepository.findAll();
    }

    // Get Sessions By User ID
    public List<InterviewSession> getSessionsByUser(Long userId) {
        return interviewSessionRepository.findByUserUserId(userId);
    }

    // Get Session By Session ID
    public Optional<InterviewSession> getSessionById(Long sessionId) {
        return interviewSessionRepository.findById(sessionId);
    }

    // Get Sessions By Status
    public List<InterviewSession> getSessionsByStatus(String status) {
        return interviewSessionRepository.findByStatus(status);
    }

    // Get Sessions By User ID And Interview Type
    public List<InterviewSession> getSessionsByUserAndType(Long userId, String interviewType) {
        return interviewSessionRepository.findByUserUserIdAndInterviewType(userId, interviewType);
    }

    // Complete Interview Session
    public Optional<InterviewSession> completeSession(Long sessionId) {

        Optional<InterviewSession> session = interviewSessionRepository.findById(sessionId);

        if (session.isPresent()) {
            InterviewSession interviewSession = session.get();

            interviewSession.setStatus("COMPLETED");
            interviewSession.setEndedAt(java.time.LocalDateTime.now());

            return Optional.of(interviewSessionRepository.save(interviewSession));
        }

        return Optional.empty();
    }
}

