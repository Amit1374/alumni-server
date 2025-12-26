package com.amit.alumniManagement.controller;

import com.amit.alumniManagement.entity.AlumniProfile;
import com.amit.alumniManagement.repository.AlumniProfileRepository;
import com.amit.alumniManagement.service.AlumniService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alumni/profile")
@AllArgsConstructor
public class AlumniController {

    private final AlumniProfileRepository alumniProfileRepository;
    private final AlumniService alumniService;

    // Filter call to search alumni
    @GetMapping("/search")
    public ResponseEntity<?> filterProfile(
            // It may present or not
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String skill
    ){
        try{
            return new ResponseEntity<>(alumniService.searchAlumni(name,companyName,skill),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> saveOrUpdateAlumniProfile(
            @PathVariable Long userId,
            @RequestBody AlumniProfile alumniProfile){
        try{
            return new ResponseEntity<>(alumniService.saveOrUpdateAlumniProfile(userId,alumniProfile),
                    HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}















