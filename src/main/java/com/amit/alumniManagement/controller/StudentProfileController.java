package com.amit.alumniManagement.controller;

import com.amit.alumniManagement.entity.StudentProfile;
import com.amit.alumniManagement.entity.User;
import com.amit.alumniManagement.repository.StudentProfileRepository;
import com.amit.alumniManagement.repository.UserRepository;
import com.amit.alumniManagement.service.StudentProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/student/profile")
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getProfile(@PathVariable Long userId) {
        try{
            return new ResponseEntity<>(studentProfileService.getStudentProfileById(userId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Profile Not Found : " + e.getMessage());
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> saveProfile(
            @PathVariable Long userId,
            @RequestBody StudentProfile profile) {

        try{
            return new ResponseEntity<>(studentProfileService.saveOrUpdateProfile(userId, profile),
                    HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to save profile : " + e.getMessage());
        }

    }
}
