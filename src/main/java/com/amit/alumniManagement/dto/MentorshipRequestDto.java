package com.amit.alumniManagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MentorshipRequestDto {

    private Long studentId;
    private Long alumniId;
    private String message;
}
