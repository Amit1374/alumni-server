package com.amit.alumniManagement.service;

import com.amit.alumniManagement.entity.MentorshipRequest;
import com.amit.alumniManagement.entity.Status;
import com.amit.alumniManagement.entity.User;
import com.amit.alumniManagement.repository.MentorshipRepository;
import com.amit.alumniManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<MentorshipRequest> requests = mentorshipRepository.findByAlumni(alumni);
        return sortRequests(requests);
    }

    // Get student mentorship request (Show in left sidebar on student dashboard)
    public List<MentorshipRequest> getRequestForStudent(Long studentId){
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<MentorshipRequest> requests = mentorshipRepository.findByStudent(student);

        return sortRequests(requests);
    }

    private List<MentorshipRequest> sortRequests(List<MentorshipRequest> requests){
        // Sort them in Java | sort it using Java's Comparator.
        // Logic: If one is PENDING and other is NOT, PENDING wins.
        //        Otherwise, compare Dates (Newest first)
        requests.sort((r1, r2) ->{ // its return null | inplace sort : that "return separately"
            boolean isPending1 = r1.getStatus().equals(Status.PENDING);
            boolean isPending2 = r2.getStatus().equals(Status.PENDING);

            // Priority 1: Status (Pending comes first)
            if(isPending1 && !isPending2) return -1; // r1 is pending it on top
            if(!isPending1 && isPending2)  return 1; // r2 is pending it on top

            return r2.getCreatedAt().compareTo(r1.getCreatedAt());
        });

        return requests;
    }


    // Method for help alumni to accept/reject req
    @Transactional
    public void updateRequestStatus(Long mentorshipRequestId, Status status){
        MentorshipRequest mentorshipRequest = mentorshipRepository.findById(mentorshipRequestId)
                .orElseThrow(() -> new RuntimeException("Mentorship request not found"));

        mentorshipRequest.setStatus(status);
        mentorshipRepository.save(mentorshipRequest);
    }
}
