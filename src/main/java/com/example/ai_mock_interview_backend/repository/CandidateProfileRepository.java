package com.example.ai_mock_interview_backend.repository;

import com.example.ai_mock_interview_backend.entity.CandidateProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CandidateProfileRepository extends JpaRepository<CandidateProfile,Long> {
    boolean existsByUser_UserId(Long userId);
    Optional<CandidateProfile> findByUser_UserId(Long userId);
}
