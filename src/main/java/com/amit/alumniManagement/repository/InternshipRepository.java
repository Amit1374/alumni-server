package com.amit.alumniManagement.repository;

import com.amit.alumniManagement.entity.Internship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship, Long> {

    List<Internship> findByMode(String mode);

    List<Internship> findBySkillsContainingIgnoreCase(String skill);

    List<Internship> findByCompanyNameContainingIgnoreCase(String company);
}
