package com.amit.alumniManagement.repository;

import com.amit.alumniManagement.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    //  Find upcoming events (Don't show old ones)
    // "After" -> checks if date > input date
    List<Event> findByEventDateTimeAfter(LocalDateTime now);

    // Find events sorted by date (Earliest first)
    List<Event> findAllByOrderByEventDateTimeAsc();
}
