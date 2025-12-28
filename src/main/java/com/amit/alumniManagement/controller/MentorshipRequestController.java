package com.amit.alumniManagement.controller;

import com.amit.alumniManagement.dto.MentorshipRequestDto;
import com.amit.alumniManagement.service.MentorshipRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/mentorship")
public class MentorshipRequestController {

    private final MentorshipRequestService mentorshipRequestService;

    @PostMapping("/send")
    public ResponseEntity<?> sendRequest(@RequestBody MentorshipRequestDto req){
        try {
            mentorshipRequestService.sendRequest(
                    req.getStudentId(),
                    req.getAlumniId(),
                    req.getMessage()
            );
            return ResponseEntity.ok("Mentorship request sent successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getStudentRequests(@PathVariable Long studentId){
        try{
            return new ResponseEntity<>(
                    mentorshipRequestService.getRequestForStudent(studentId),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error while getting mentorship request" + e.getMessage());
        }
    }
}





















