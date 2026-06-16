package com.amit.alumniManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "internships")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;               // Backend Intern
    private String companyName;         // Google, Infosys
    private String description;

    private String skills;              // Java, React, SQL
    private String mode;                // Remote / Onsite / Hybrid
    private Integer durationInMonths;   // 3, 6
    private boolean paid;

    private LocalDate postedDate;

    @ManyToOne
    @JoinColumn(name = "posted_by", nullable = false)
    private User postedBy;               // Alumni or Admin
}
