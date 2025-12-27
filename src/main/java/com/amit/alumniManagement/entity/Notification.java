package com.amit.alumniManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Builder.Default        // Ensures Builder uses this default value
    private boolean isRead = false;

    // "When did this arrive?" (For sorting)
    @CreationTimestamp
    private LocalDateTime createdAt;

}
