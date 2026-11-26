package com.example.ai_mock_interview_backend.controller;

import com.example.ai_mock_interview_backend.entity.Resume;
import com.example.ai_mock_interview_backend.service.ResumeService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/resumes")
@CrossOrigin(origins = "http://localhost:5173")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping("/upload/{userId}")
    public ResponseEntity<?> uploadResume(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file) {

        try {
            Resume resume = resumeService.uploadResume(file, userId);

            return ResponseEntity.ok(resume);

        } catch (IllegalArgumentException e) {

            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (IOException e) {

            return ResponseEntity.internalServerError()
                    .body("Error uploading resume");

        } catch (RuntimeException e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
