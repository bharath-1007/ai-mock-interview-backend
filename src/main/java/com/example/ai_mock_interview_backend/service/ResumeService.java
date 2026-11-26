package com.example.ai_mock_interview_backend.service;

import com.example.ai_mock_interview_backend.entity.Resume;
import com.example.ai_mock_interview_backend.entity.User;
import com.example.ai_mock_interview_backend.repository.ResumeRepository;
import com.example.ai_mock_interview_backend.repository.UserRepository;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public ResumeService(ResumeRepository resumeRepository,
                         UserRepository userRepository) {
        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
    }

    public Resume uploadResume(MultipartFile file, Long userId)
            throws IOException {

        // Check whether file is empty
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Please select a PDF file");
        }

        // Check PDF extension
        String originalFileName = file.getOriginalFilename();

        if (originalFileName == null ||
                !originalFileName.toLowerCase().endsWith(".pdf")) {
            throw new IllegalArgumentException("Only PDF files are allowed");
        }

        // Find user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create upload directory
        Path uploadDirectory = Path.of("uploads", "resumes");
        Files.createDirectories(uploadDirectory);

        // Generate unique filename
        String storedFileName = UUID.randomUUID() + ".pdf";

        Path filePath = uploadDirectory.resolve(storedFileName);

        // Save PDF file
        Files.copy(file.getInputStream(), filePath);

        try {
            // Extract text from PDF
            String extractedText;

            try (PDDocument document =
                         Loader.loadPDF(filePath.toFile())) {

                PDFTextStripper stripper = new PDFTextStripper();
                extractedText = stripper.getText(document);
            }

            // Create Resume object
            Resume resume = new Resume();

            resume.setUser(user);
            resume.setFileName(originalFileName);
            resume.setResumePath(filePath.toString());
            resume.setExtractedText(extractedText);

            // Save resume details in database
            return resumeRepository.save(resume);

        } catch (IOException | RuntimeException e) {

            // Delete uploaded file if processing fails
            Files.deleteIfExists(filePath);
            throw e;
        }
    }
}

