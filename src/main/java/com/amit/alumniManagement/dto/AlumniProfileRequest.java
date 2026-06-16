package com.amit.alumniManagement.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlumniProfileRequest {

    private Integer passOutYear;
    private Integer experienceInYear;
    private String expertise;
    private String companyName;
    private String designation;
    private String industry;
}
