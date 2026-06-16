package com.amit.alumniManagement.repository;

import com.amit.alumniManagement.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    //  Get ALL messages for a user (Sorted: Newest on top)
    // "UserId" -> Look at User object, then look at ID field
    List<Notification> findByUserIdOrderByCreatedAtDesc(Long userId);

    //  Get only UNREAD messages (The "Badge" count)
    // "IsReadFalse" -> Checks if boolean isRead == false
    List<Notification> findByUserIdAndIsReadFalse(Long userId);

    List<Notification> findByUserId(Long userId);

}
