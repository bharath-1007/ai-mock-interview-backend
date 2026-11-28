package com.example.ai_mock_interview_backend.service;

import com.example.ai_mock_interview_backend.entity.InterviewQuestion;
import com.example.ai_mock_interview_backend.entity.InterviewSession;
import com.example.ai_mock_interview_backend.repository.InterviewQuestionRepository;
import com.example.ai_mock_interview_backend.repository.InterviewSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.ai_mock_interview_backend.client.AIServiceClient;
import com.example.ai_mock_interview_backend.dto.QuestionRequestDTO;
import com.example.ai_mock_interview_backend.dto.QuestionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class InterviewQuestionService {

    @Autowired
    private InterviewQuestionRepository interviewQuestionRepository;

    @Autowired
    private InterviewSessionRepository interviewSessionRepository;

    @Autowired
    private AIServiceClient aiServiceClient;

    public InterviewQuestion generateQuestion(Long sessionId) {

        // Step 1: Retrieve interview session
        InterviewSession session = interviewSessionRepository
                .findById(sessionId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Interview session not found"
                ));

        // Step 2: Check interview status
        if (!"IN_PROGRESS".equals(session.getStatus())) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Interview session is not active"
            );
        }

        // Step 3: Prepare interview details
        String interviewType = session.getInterviewType();
        String difficulty = session.getDifficulty();
        String topic = session.getTopic();
        String resumeText = null;

        // Step 4: Validate interview type
        if ("RESUME_BASED".equals(interviewType)) {

            if (session.getResume() == null) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Resume not found for this session"
                );
            }

            resumeText = session.getResume().getExtractedText();

            if (resumeText == null || resumeText.isBlank()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Resume text is empty"
                );
            }

        } else if ("TECHNICAL".equals(interviewType)) {

            if (topic == null || topic.isBlank()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Interview topic is required"
                );
            }
        }

        // Step 5: Create request DTO
        QuestionRequestDTO request = new QuestionRequestDTO(
                interviewType,
                topic,
                difficulty,
                resumeText
        );

        // Step 6: Call Python AI service
        QuestionResponseDTO response =
                aiServiceClient.generateQuestion(request);

        // Step 7: Create InterviewQuestion entity
        InterviewQuestion question = new InterviewQuestion();

        question.setInterviewSession(session);
        question.setQuestionText(response.getQuestionText());
        question.setDifficulty(session.getDifficulty());

        // Step 8: Save question in MySQL
        return interviewQuestionRepository.save(question);
    }



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
