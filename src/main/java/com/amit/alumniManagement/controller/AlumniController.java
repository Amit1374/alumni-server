package com.amit.alumniManagement.controller;

import com.amit.alumniManagement.dto.AlumniProfileRequest;
import com.amit.alumniManagement.repository.AlumniProfileRepository;
import com.amit.alumniManagement.service.AlumniService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAlumniProfile(@PathVariable Long userId) {
        return alumniProfileRepository.findByUserId(userId)
                .map(profile -> ResponseEntity.ok(profile))
                .orElse(ResponseEntity.ok().build()); // return empty if not found
    }


    @PostMapping("/{userId}")
    public ResponseEntity<?> saveOrUpdateAlumniProfile(
            @PathVariable Long userId,
            @RequestPart("alumni-profile") String alumniProfileString, // @RequestBody cannot handle multipart
            @RequestPart(value = "file", required = false) MultipartFile file
    ){
        // parse "multipart/form-data" as string to req obj
        ObjectMapper mapper = new ObjectMapper();
        AlumniProfileRequest request = null;
        try{
            request = mapper.readValue(alumniProfileString, AlumniProfileRequest.class);
            return new ResponseEntity<>(
                    alumniService.saveOrUpdateAlumniProfile(userId, request, file),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}















