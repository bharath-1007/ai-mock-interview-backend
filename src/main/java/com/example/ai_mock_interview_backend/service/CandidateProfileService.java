package com.example.ai_mock_interview_backend.service;

import com.example.ai_mock_interview_backend.entity.CandidateProfile;
import com.example.ai_mock_interview_backend.entity.User;
import com.example.ai_mock_interview_backend.repository.CandidateProfileRepository;
import com.example.ai_mock_interview_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CandidateProfileService {
    @Autowired
    private CandidateProfileRepository profileRepository;
    @Autowired
    private UserRepository userRepository;

    public CandidateProfile createProfile(Long userId, CandidateProfile profile) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "User not found"));

        if (profileRepository.existsByUser_UserId(userId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Profile already exists");
        }

        profile.setUser(user);

        return profileRepository.save(profile);
    }

    public CandidateProfile getProfileByUserId(Long userId) {

        return profileRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Profile not found"));
    }

    public CandidateProfile updateProfile(Long userId, CandidateProfile updatedProfile) {

        CandidateProfile existingProfile = getProfileByUserId(userId);

        existingProfile.setSkills(updatedProfile.getSkills());
        existingProfile.setExperienceYears(updatedProfile.getExperienceYears());
        existingProfile.setEducation(updatedProfile.getEducation());

        return profileRepository.save(existingProfile);
    }


    public void deleteProfile(Long userId) {

        CandidateProfile profile = getProfileByUserId(userId);

        profileRepository.delete(profile);
    }

}
