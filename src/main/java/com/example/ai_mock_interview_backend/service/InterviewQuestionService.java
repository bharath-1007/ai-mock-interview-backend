package com.example.ai_mock_interview_backend.service;

import com.example.ai_mock_interview_backend.entity.InterviewQuestion;
import com.example.ai_mock_interview_backend.entity.InterviewSession;
import com.example.ai_mock_interview_backend.repository.InterviewQuestionRepository;
import com.example.ai_mock_interview_backend.repository.InterviewSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InterviewQuestionService {

    @Autowired
    private InterviewQuestionRepository interviewQuestionRepository;

    @Autowired
    private InterviewSessionRepository interviewSessionRepository;

    // Save interview question
    public InterviewQuestion saveQuestion(InterviewQuestion question) {

        Long sessionId = question.getInterviewSession().getSessionId();

        InterviewSession session = interviewSessionRepository
                .findById(sessionId)
                .orElseThrow(() ->
                        new RuntimeException("Interview session not found"));

        question.setInterviewSession(session);

        return interviewQuestionRepository.save(question);
    }

    // Get all interview questions
    public List<InterviewQuestion> getAllQuestions() {

        return interviewQuestionRepository.findAll();
    }

    // Get question by ID
    public InterviewQuestion getQuestionById(Long questionId) {

        return interviewQuestionRepository.findById(questionId)
                .orElseThrow(() ->
                        new RuntimeException("Question not found"));
    }

    // Get questions by interview session ID
    public List<InterviewQuestion> getQuestionsBySessionId(Long sessionId) {

        return interviewQuestionRepository
                .findByInterviewSession_SessionIdOrderByQuestionIdAsc(sessionId);
    }
}
