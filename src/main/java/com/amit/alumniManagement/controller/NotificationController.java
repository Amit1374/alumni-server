package com.amit.alumniManagement.controller;

import com.amit.alumniManagement.entity.Notification;
import com.amit.alumniManagement.entity.User;
import com.amit.alumniManagement.repository.NotificationRepository;
import com.amit.alumniManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {

    private final UserRepository userRepository;

    private final NotificationRepository notificationRepository;

    // Principal class's obj : When a user logs in (via Basic Auth or JWT),
    // Spring Security stores their identity in the background.
    // You can access it just by adding Principal principal to your controller method arguments.
    @GetMapping
    public ResponseEntity<?> getUserNotifications(Principal principal) {
        try {
            // Fetch user
            String email = principal.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            List<Notification> notifications = notificationRepository.findByUserIdAndIsReadFalse(user.getId());

            return ResponseEntity.ok(notifications);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error" + e.getMessage());
        }
    }
}
