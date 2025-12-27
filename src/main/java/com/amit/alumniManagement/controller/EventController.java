package com.amit.alumniManagement.controller;

import com.amit.alumniManagement.dto.EventRequest;
import com.amit.alumniManagement.entity.Event;
import com.amit.alumniManagement.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@AllArgsConstructor
@RequestMapping("api/events")
public class EventController {

    private final EventService eventService;

    @PostMapping("/create")
    // @PreAuthorize("hasRole('ADMIN')") // CRITICAL: Only Admin can touch this
    public ResponseEntity<?> createEvent(
            @RequestBody EventRequest newEvent
    ){
        try{
            Event event = Event.builder()
                    .eventName(newEvent.getEventName())
                    .eventDescription(newEvent.getEventDescription())
                    .eventLocation(newEvent.getEventLocation())
                    .eventDateTime(LocalDateTime.parse(newEvent.getEventDateTime()))
                    .build();

            eventService.createEvent(event);
            return ResponseEntity.ok("Event created");
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Error while creating event" + e.getMessage());
        }
    }
}
