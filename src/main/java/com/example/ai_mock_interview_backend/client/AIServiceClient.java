package com.example.ai_mock_interview_backend.client;

import com.example.ai_mock_interview_backend.dto.QuestionRequestDTO;
import com.example.ai_mock_interview_backend.dto.QuestionResponseDTO;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@Component
public class AIServiceClient {

    private final RestClient restClient;

    public AIServiceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public QuestionResponseDTO generateQuestion(
            QuestionRequestDTO request) {

        try {

            QuestionResponseDTO response = restClient.post()
                    .uri("/generate-question")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(request)
                    .retrieve()
                    .body(QuestionResponseDTO.class);

            if (response == null ||
                    response.getQuestionText() == null ||
                    response.getQuestionText().isBlank()) {

                throw new ResponseStatusException(
                        HttpStatus.BAD_GATEWAY,
                        "AI service returned an empty question"
                );
            }

            return response;

        } catch (RestClientException e) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_GATEWAY,
                    "AI question generation failed",
                    e
            );
        }
    }
}
