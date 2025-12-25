package com.amit.alumniManagement.controller;



import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/student")
    public String studentApi() {
        return "Student access success";
    }

    @GetMapping("/alumni")
    public String alumniApi() {
        return "Alumni access success";
    }

    @GetMapping("/admin")
    public String adminApi() {
        return "Admin access success";
    }
}
