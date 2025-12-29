package com.amit.alumniManagement.repository;

import com.amit.alumniManagement.entity.MentorshipRequest;
import com.amit.alumniManagement.entity.Status;
import com.amit.alumniManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorshipRepository extends JpaRepository<MentorshipRequest, Long> {

    // The "Spam Checker" (For validation)
    // Checks if a specific student has already requested a specific alumni
    boolean existsByStudentAndAlumni(User student, User alumni);

    // For Alumni Dashboard: "Show me all requests sent TO me"
    List<MentorshipRequest> findByAlumniAndStatus(User alumni, Status status);

    // For Student Dashboard: "Show me all requests I sent"
    List<MentorshipRequest> findByStudentAndStatus(User student, Status status);

    List<MentorshipRequest> findByStudent(User student);

    List<MentorshipRequest> findByAlumni(User alumni);

}
