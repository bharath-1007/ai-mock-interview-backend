package com.example.ai_mock_interview_backend.repository;

import com.example.ai_mock_interview_backend.entity.CandidateProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateProfileRepository extends JpaRepository<CandidateProfile,Long> {

}
