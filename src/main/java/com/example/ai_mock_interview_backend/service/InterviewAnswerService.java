package com.example.ai_mock_interview_backend.service;

import com.example.ai_mock_interview_backend.entity.InterviewAnswer;
import com.example.ai_mock_interview_backend.entity.InterviewSession;
import com.example.ai_mock_interview_backend.entity.InterviewQuestion;
import com.example.ai_mock_interview_backend.repository.InterviewAnswerRepository;
import com.example.ai_mock_interview_backend.repository.InterviewSessionRepository;
import com.example.ai_mock_interview_backend.repository.InterviewQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InterviewAnswerService {

    @Autowired
    private InterviewAnswerRepository interviewAnswerRepository;

    @Autowired
    private InterviewSessionRepository interviewSessionRepository;

    @Autowired
    private InterviewQuestionRepository interviewQuestionRepository;

    // Save candidate answer
    public InterviewAnswer saveAnswer(InterviewAnswer answer) {

        Long sessionId = answer.getInterviewSession().getSessionId();

        Long questionId = answer.getInterviewQuestion().getQuestionId();

        InterviewSession session = interviewSessionRepository
                .findById(sessionId)
                .orElseThrow(() ->
                        new RuntimeException("Interview session not found"));

        InterviewQuestion question = interviewQuestionRepository
                .findById(questionId)
                .orElseThrow(() ->
                        new RuntimeException("Interview question not found"));

        // Validate question belongs to the session
        if (!question.getInterviewSession().getSessionId().equals(sessionId)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Question does not belong to this interview session");
        }

        answer.setInterviewSession(session);
        answer.setInterviewQuestion(question);

        // AI evaluation will be implemented later
        answer.setAnswerId(null);
        answer.setAiScore(null);
        answer.setFeedback(null);

        return interviewAnswerRepository.save(answer);
    }

    // Get all answers
    public List<InterviewAnswer> getAllAnswers() {

        return interviewAnswerRepository.findAll();
    }

    // Get answer by ID
    public InterviewAnswer getAnswerById(Long answerId) {

        return interviewAnswerRepository.findById(answerId)
                .orElseThrow(() ->
                        new RuntimeException("Interview answer not found"));
    }

    // Get answers by session ID
    public List<InterviewAnswer> getAnswersBySessionId(Long sessionId) {

        return interviewAnswerRepository
                .findByInterviewSession_SessionId(sessionId);
    }

    // Get answers by question ID
    public List<InterviewAnswer> getAnswersByQuestionId(Long questionId) {

        return interviewAnswerRepository
                .findByInterviewQuestion_QuestionId(questionId);
    }
}