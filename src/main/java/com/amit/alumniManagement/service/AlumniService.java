package com.amit.alumniManagement.service;

import com.amit.alumniManagement.dto.AlumniProfileRequest;
import com.amit.alumniManagement.entity.AlumniProfile;
import com.amit.alumniManagement.entity.User;
import com.amit.alumniManagement.repository.AlumniProfileRepository;
import com.amit.alumniManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlumniService {

    private final UserRepository userRepository;

    private final AlumniProfileRepository alumniProfileRepository;

    private final FileUploadService fileUploadService;

    public AlumniProfile saveOrUpdateAlumniProfile(Long userId, AlumniProfileRequest alumniProfileRequest, MultipartFile profileImage) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new  ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User not found"
        ));

        // improved code
        AlumniProfile profile = alumniProfileRepository.findByUserId(userId)
                .orElseGet(() -> {
                    AlumniProfile newAlumniProfile = new AlumniProfile();
                    newAlumniProfile.setUser(user);
                    return newAlumniProfile;
                });


        AlumniProfile newAlumniProfile = convertToEntity(alumniProfileRequest);

        // update fields
        profile.setPassOutYear(newAlumniProfile.getPassOutYear());
        profile.setExpertise(newAlumniProfile.getExpertise());
        profile.setExperienceInYear(newAlumniProfile.getExperienceInYear());
        profile.setCompanyName(newAlumniProfile.getCompanyName());
        profile.setDesignation(newAlumniProfile.getDesignation());

        if(profileImage != null &&  !profileImage.isEmpty() ) {

            // delete old image from Cloudinary
            if (profile.getImgUrl() != null) {
                fileUploadService.deleteFile(profile.getImgUrl());
            }

            String imgUrl = fileUploadService.uploadFile(profileImage);
            profile.setImgUrl(imgUrl);
        }

        return alumniProfileRepository.save(profile);
    }


    // Search Alumni
    public List<AlumniProfile> searchAlumni(String name, String company, String skill) {
        try {
            // Step 1: Start with ALL alumni
            List<AlumniProfile> profiles = alumniProfileRepository.findAll();

            // Step 2: Filter by Name (if provided)
            if (name != null && !name.isEmpty()) {
                profiles = profiles.stream()
                        .filter(p -> p.getUser().getName().toLowerCase().contains(name.toLowerCase()))
                        .collect(Collectors.toList());
            }

            // Step 3: Filter by Company (if provided)
            // Notice: We use the 'profiles' list from Step 2, so we keep narrowing it down!
            if (company != null && !company.isEmpty()) {
                profiles = profiles.stream()
                        .filter(p -> p.getCompanyName() != null &&
                                p.getCompanyName().toLowerCase().contains(company.toLowerCase()))
                        .collect(Collectors.toList());
            }

            // Step 4: Filter by Skill (if provided)
            if (skill != null && !skill.isEmpty()) {
                profiles = profiles.stream()
                        .filter(p -> p.getExpertise() != null &&
                                p.getExpertise().toLowerCase().contains(skill.toLowerCase()))
                        .collect(Collectors.toList());
            }

            return profiles;

        } catch (Exception e) {
            throw new RuntimeException("Error searching alumni: " + e.getMessage());
        }
    }

    public AlumniProfile getAlumniProfileById(Long userId){
        return alumniProfileRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private AlumniProfile convertToEntity(AlumniProfileRequest alumniProfileRequest) {
        return AlumniProfile.builder()
                .passOutYear(alumniProfileRequest.getPassOutYear())
                .experienceInYear(alumniProfileRequest.getExperienceInYear())
                .expertise(alumniProfileRequest.getExpertise())
                .companyName(alumniProfileRequest.getCompanyName())
                .designation(alumniProfileRequest.getDesignation())
                .industry(alumniProfileRequest.getIndustry())
                .build();
    }

}

























