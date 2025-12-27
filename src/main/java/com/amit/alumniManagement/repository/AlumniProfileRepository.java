package com.amit.alumniManagement.repository;

import com.amit.alumniManagement.entity.AlumniProfile;
import com.amit.alumniManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlumniProfileRepository extends JpaRepository<AlumniProfile, Long> {

    // Search by company
    List<AlumniProfile> findByCompanyNameIgnoreCase(String companyName);

    // Search by expertise
    List<AlumniProfile> findByExpertiseIgnoreCase(String expertise);

    // Search by pass out year
    List<AlumniProfile> findByPassOutYear(Integer year);

    // Search by industry
    List<AlumniProfile> findByIndustryIgnoreCase(String industry);

    // Search by alumni name (User -> name)
    List<AlumniProfile> findByUser_NameIgnoreCase(String name);

    // Fetch profile by user id (MOST IMPORTANT)
    Optional<AlumniProfile> findByUserId(Long userId);

    // Combined search
    List<AlumniProfile> findByCompanyNameIgnoreCaseAndDesignationIgnoreCase(
            String companyName,
            String designation
    );

    Optional<Object> findByUser(User user);
}
