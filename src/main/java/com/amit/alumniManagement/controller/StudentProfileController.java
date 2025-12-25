package com.amit.alumniManagement.controller;

import com.amit.alumniManagement.entity.StudentProfile;
import com.amit.alumniManagement.entity.User;
import com.amit.alumniManagement.repository.StudentProfileRepository;
import com.amit.alumniManagement.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/profile")
public class StudentProfileController {

    private final StudentProfileRepository repo;
    private final UserRepository userRepo;

    public StudentProfileController(StudentProfileRepository repo,
                                    UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @GetMapping("/{userId}")
    public StudentProfile getProfile(@PathVariable Long userId) {
        return repo.findByUserId(userId).orElse(null);
    }

    @PostMapping("/{userId}")
    public StudentProfile saveProfile(
            @PathVariable Long userId,
            @RequestBody StudentProfile profile) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return repo.findByUserId(userId)
                .map(existing -> {
                    existing.setDepartment(profile.getDepartment());
                    existing.setYear(profile.getYear());
                    existing.setSkills(profile.getSkills());
                    return repo.save(existing);
                })
                .orElseGet(() -> {
                    profile.setUser(user);
                    return repo.save(profile);
                });
    }
}
