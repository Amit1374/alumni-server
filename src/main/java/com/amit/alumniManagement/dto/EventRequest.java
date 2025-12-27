package com.amit.alumniManagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventRequest {

    private String eventName;
    private String eventDescription;
    private String eventLocation;
    private String eventDateTime;
}
