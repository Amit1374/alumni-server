package com.amit.alumniManagement.service;

import com.amit.alumniManagement.entity.AlumniProfile;
import com.amit.alumniManagement.entity.User;
import com.amit.alumniManagement.repository.AlumniProfileRepository;
import com.amit.alumniManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AlumniService {

    private final UserRepository userRepository;

    private final AlumniProfileRepository alumniProfileRepository;

    public AlumniProfile saveOrUpdateAlumniProfile(Long userId, AlumniProfile alumniProfile){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return alumniProfileRepository.findByUserId(userId)
                .map(existing -> {
                    existing.setPassOutYear(alumniProfile.getPassOutYear());
                    existing.setExpertise(alumniProfile.getExpertise());
                    existing.setExperienceInYear(alumniProfile.getExperienceInYear());
                    existing.setCompanyName(alumniProfile.getCompanyName());
                    existing.setDesignation(alumniProfile.getDesignation());
                    return alumniProfileRepository.save(existing);
                })
                .orElseGet(() -> {
                    alumniProfile.setUser(user);
                    return alumniProfileRepository.save(alumniProfile);
                });

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

}

























