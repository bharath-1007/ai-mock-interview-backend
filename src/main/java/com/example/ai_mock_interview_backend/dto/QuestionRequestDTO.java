package com.example.ai_mock_interview_backend.dto;

public class QuestionRequestDTO {

    private String interviewType;
    private String topic;
    private String difficulty;
    private String resumeText;

    public QuestionRequestDTO() {
    }

    public QuestionRequestDTO(String interviewType,
                              String topic,
                              String difficulty,
                              String resumeText) {

        this.interviewType = interviewType;
        this.topic = topic;
        this.difficulty = difficulty;
        this.resumeText = resumeText;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public void setInterviewType(String interviewType) {
        this.interviewType = interviewType;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getResumeText() {
        return resumeText;
    }

    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }
}
