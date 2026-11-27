package com.example.ai_mock_interview_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "interview_questions")
public class InterviewQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private InterviewSession interviewSession;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String questionText;

    @Column(nullable = false)
    private String difficulty;

    private LocalDateTime createdAt;

    public InterviewQuestion() {
    }

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public InterviewSession getInterviewSession() {
        return interviewSession;
    }

    public void setInterviewSession(
            InterviewSession interviewSession) {
        this.interviewSession = interviewSession;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
