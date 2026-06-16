package com.amit.alumniManagement.service;

import com.amit.alumniManagement.entity.Event;
import com.amit.alumniManagement.entity.Notification;
import com.amit.alumniManagement.entity.User;
import com.amit.alumniManagement.repository.EventRepository;
import com.amit.alumniManagement.repository.NotificationRepository;
import com.amit.alumniManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private final NotificationRepository notificationRepository;

    public void createEvent(Event upComingEvent) {

        Event savedEvent = eventRepository.save(upComingEvent);

        List<User> users = userRepository.findAll();

        // Create list of notification to store each notification for each user
        List<Notification> notifications = new ArrayList<>();

        for (User user : users){
            // we store/ create notification table entries direct while create event
            Notification notification = Notification.builder()
                    .user(user)
                    .event(savedEvent)
                    .message("New event : " + savedEvent.getEventName())
                    .isRead(false)
                    .build();
            notifications.add(notification);
        }

        // Each list obj treat as separate row
        notificationRepository.saveAll(notifications);
    }
}
