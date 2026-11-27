package com.example.ai_mock_interview_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.ai_mock_interview_backend.entity.InterviewSession;
import com.example.ai_mock_interview_backend.service.InterviewSessionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/interview-sessions")
@CrossOrigin(origins = "http://localhost:5173")
public class InterviewSessionController {

    @Autowired
    private InterviewSessionService interviewSessionService;

    // Create Interview Session
    @PostMapping
    public InterviewSession createSession(@RequestBody InterviewSession session) {
        return interviewSessionService.createSession(session);
    }

    // Get All Interview Sessions
    @GetMapping
    public List<InterviewSession> getAllSessions() {
        return interviewSessionService.getAllSessions();
    }

    // Get Sessions By User ID
    @GetMapping("/user/{userId}")
    public List<InterviewSession> getSessionsByUser(@PathVariable Long userId) {
        return interviewSessionService.getSessionsByUser(userId);
    }

    // Get Session By Session ID
    @GetMapping("/{sessionId}")
    public Optional<InterviewSession> getSessionById(@PathVariable Long sessionId) {
        return interviewSessionService.getSessionById(sessionId);
    }

    // Get Sessions By Status
    @GetMapping("/status/{status}")
    public List<InterviewSession> getSessionsByStatus(@PathVariable String status) {
        return interviewSessionService.getSessionsByStatus(status);
    }

    // Get Sessions By User ID And Interview Type
    @GetMapping("/user/{userId}/type/{interviewType}")
    public List<InterviewSession> getSessionsByUserAndType(
            @PathVariable Long userId,
            @PathVariable String interviewType) {

        return interviewSessionService.getSessionsByUserAndType(userId, interviewType);
    }

    // Complete Interview Session
    @PutMapping("/{sessionId}/complete")
    public Optional<InterviewSession> completeSession(@PathVariable Long sessionId) {
        return interviewSessionService.completeSession(sessionId);
    }
}

