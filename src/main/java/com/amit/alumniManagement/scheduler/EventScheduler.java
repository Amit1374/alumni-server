package com.amit.alumniManagement.scheduler;

import com.amit.alumniManagement.entity.Event;
import com.amit.alumniManagement.repository.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class EventScheduler {

    private EventRepository eventRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteEventByEventDateTimeBefore() {
        LocalDateTime now = LocalDateTime.now(); // calculate time once

        eventRepository.deleteByEventDateTimeBefore(now);

        System.out.println("Deleted expired events at " + now);
    }
}
