package com.amit.alumniManagement.service;

import com.amit.alumniManagement.entity.StudentProfile;
import com.amit.alumniManagement.entity.User;
import com.amit.alumniManagement.repository.StudentProfileRepository;
import com.amit.alumniManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;

    private final UserRepository userRepository;

    // Send profile if exits
    public StudentProfile getStudentProfileById(Long userId){
        return studentProfileRepository.findByUserId(userId)
                .orElse(null);
    }

    public StudentProfile saveOrUpdateProfile(Long userId, StudentProfile studentProfile){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return studentProfileRepository.findByUserId(userId)
                .map(existing -> {
                    // Update existing
                    existing.setDepartment(studentProfile.getDepartment());
                    existing.setYear(studentProfile.getYear());
                    existing.setSkills(studentProfile.getSkills());
                    existing.setInterests(studentProfile.getInterests());
                    return studentProfileRepository.save(existing);
                })
                .orElseGet(() -> {
                    // Create new
                    studentProfile.setUser(user);
                    return studentProfileRepository.save(studentProfile);
                });
    }
}
