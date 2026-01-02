package com.amit.alumniManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alumni_profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlumniProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer passOutYear;
    private Integer experienceInYear;
    private String expertise;
    private String companyName;
    private String designation;
    private String industry;
    private String imgUrl;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;
}
