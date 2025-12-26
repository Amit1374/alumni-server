package com.amit.alumniManagement.service;

import com.amit.alumniManagement.entity.MentorshipRequest;
import com.amit.alumniManagement.entity.Status;
import com.amit.alumniManagement.entity.User;
import com.amit.alumniManagement.repository.MentorshipRepository;
import com.amit.alumniManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MentorshipRequestService {

    private final MentorshipRepository mentorshipRepository;

    private final UserRepository userRepository;


    public void sendRequest(Long studentId, Long alumniId, String message){

        // fetching users
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        User alumni = userRepository.findById(alumniId)
                .orElseThrow(() -> new RuntimeException("Alumni not found"));

        // check req should b/w student and alumni
        if(alumni.getRole().equals(student.getRole())){
            throw new RuntimeException("The user you are trying to message is not an Alumni");
        }

        // Validation: Duplicate Check (Your specific logic)
        if (mentorshipRepository.existsByStudentAndAlumni(student, alumni)) {
            throw new RuntimeException("You have already sent a request to this Alumni.");
        }

        mentorshipRepository.save(
                MentorshipRequest.builder()
                        .student(student)
                        .alumni(alumni)
                        .message(message)
                        .status(Status.PENDING)
                        .build()
        );
    }


    // Helper method for the Alumni Dashboard
    public List<MentorshipRequest> getRequestForAlumni(Long alumniId){
        User alumni = userRepository.findById(alumniId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mentorshipRepository.findByAlumniAndStatus(alumni, Status.PENDING);
    }

}
