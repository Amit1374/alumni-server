package com.amit.alumniManagement.service;

import com.amit.alumniManagement.entity.Internship;
import com.amit.alumniManagement.entity.User;
import com.amit.alumniManagement.repository.InternshipRepository;
import com.amit.alumniManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipService {

    private final InternshipRepository internshipRepository;
    private final UserRepository userRepository;

    public Internship postInternship(Long userId, Internship internship) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        internship.setPostedBy(user);
        internship.setPostedDate(LocalDate.now());

        return internshipRepository.save(internship);
    }

    public List<Internship> getAllInternships() {
        return internshipRepository.findAll();
    }
}
