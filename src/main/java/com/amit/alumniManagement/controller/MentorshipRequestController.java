package com.amit.alumniManagement.controller;

import com.amit.alumniManagement.dto.MentorshipRequestDto;
import com.amit.alumniManagement.service.MentorshipRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



















}
