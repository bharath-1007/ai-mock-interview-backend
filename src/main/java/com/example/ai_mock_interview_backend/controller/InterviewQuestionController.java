package com.example.ai_mock_interview_backend.controller;

import com.example.ai_mock_interview_backend.entity.InterviewQuestion;
import com.example.ai_mock_interview_backend.service.InterviewQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class InterviewQuestionController {

    @Autowired
    private InterviewQuestionService interviewQuestionService;

    // Save interview question
    @PostMapping
    public InterviewQuestion saveQuestion(
            @RequestBody InterviewQuestion question) {

        return interviewQuestionService.saveQuestion(question);
    }

    // Get all questions
    @GetMapping
    public List<InterviewQuestion> getAllQuestions() {

        return interviewQuestionService.getAllQuestions();
    }

    // Get question by ID
    @GetMapping("/{questionId}")
    public InterviewQuestion getQuestionById(
            @PathVariable Long questionId) {

        return interviewQuestionService.getQuestionById(questionId);
    }

    // Get questions by session ID
    @GetMapping("/session/{sessionId}")
    public List<InterviewQuestion> getQuestionsBySessionId(
            @PathVariable Long sessionId) {

        return interviewQuestionService
                .getQuestionsBySessionId(sessionId);
    }
}
