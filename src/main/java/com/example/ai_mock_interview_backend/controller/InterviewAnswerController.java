package com.example.ai_mock_interview_backend.controller;

import com.example.ai_mock_interview_backend.entity.InterviewAnswer;
import com.example.ai_mock_interview_backend.service.InterviewAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class InterviewAnswerController {

    @Autowired
    private InterviewAnswerService interviewAnswerService;

    // Save candidate answer
    @PostMapping
    public InterviewAnswer saveAnswer(
            @RequestBody InterviewAnswer answer) {

        return interviewAnswerService.saveAnswer(answer);
    }

    // Get all answers
    @GetMapping
    public List<InterviewAnswer> getAllAnswers() {

        return interviewAnswerService.getAllAnswers();
    }

    // Get answer by ID
    @GetMapping("/{answerId}")
    public InterviewAnswer getAnswerById(
            @PathVariable Long answerId) {

        return interviewAnswerService.getAnswerById(answerId);
    }

    // Get answers by session ID
    @GetMapping("/session/{sessionId}")
    public List<InterviewAnswer> getAnswersBySessionId(
            @PathVariable Long sessionId) {

        return interviewAnswerService
                .getAnswersBySessionId(sessionId);
    }

    // Get answers by question ID
    @GetMapping("/question/{questionId}")
    public List<InterviewAnswer> getAnswersByQuestionId(
            @PathVariable Long questionId) {

        return interviewAnswerService
                .getAnswersByQuestionId(questionId);
    }
}
