package com.amit.alumniManagement.controller;

import com.amit.alumniManagement.entity.Internship;
import com.amit.alumniManagement.service.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipService internshipService;

    // ✅ Alumni / Admin posts internship
    @PostMapping("/{userId}")
    public Internship postInternship(
            @PathVariable Long userId,
            @RequestBody Internship internship
    ) {
        return internshipService.postInternship(userId, internship);
    }

    // ✅ Students view internships
    @GetMapping
    public List<Internship> getAllInternships() {
        return internshipService.getAllInternships();
    }
}
