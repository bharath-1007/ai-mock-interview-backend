package com.example.ai_mock_interview_backend.controller;

import com.example.ai_mock_interview_backend.entity.CandidateProfile;
import com.example.ai_mock_interview_backend.service.CandidateProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate-profiles")
public class CandidateProfileController {
    @Autowired
    private CandidateProfileService profileService;

    @PostMapping("/{userId}")
    public ResponseEntity<CandidateProfile> createProfile(
            @PathVariable Long userId,
            @RequestBody CandidateProfile profile) {

        return new ResponseEntity<>(
                profileService.createProfile(userId, profile),
                HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CandidateProfile> getProfile(@PathVariable Long userId) {

        return ResponseEntity.ok(
                profileService.getProfileByUserId(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<CandidateProfile> updateProfile(
            @PathVariable Long userId,
            @RequestBody CandidateProfile profile) {

        return ResponseEntity.ok(
                profileService.updateProfile(userId, profile));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long userId) {

        profileService.deleteProfile(userId);

        return ResponseEntity.noContent().build();
    }
}
