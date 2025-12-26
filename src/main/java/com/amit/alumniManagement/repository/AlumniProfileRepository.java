package com.amit.alumniManagement.repository;

import com.amit.alumniManagement.entity.AlumniProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlumniProfileRepository extends JpaRepository<AlumniProfile, Long> {

    // We add here ignore case while searching/ find

    List<AlumniProfile> findByCompanyName(String companyName);

    List<AlumniProfile> findByExpertise(String skill);

    List<AlumniProfile> findByPassOutYear(Integer year);

    List<AlumniProfile> findByIndustry(String industry);

    // "User_Name" tells Spring: "Go to User object, then check Name field"
    List<AlumniProfile> findByUserName(String name);

    Optional<AlumniProfile> findByUserId(Long userId);

    List<AlumniProfile> findByCompanyNameAndDesignation(String companyName,  String designation);
}
