package com.example.ai_mock_interview_backend.repository;

import com.example.ai_mock_interview_backend.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResumeRepository extends JpaRepository<Resume,Long> {
    List<Resume> findByUserUserId(Long userId);
}
