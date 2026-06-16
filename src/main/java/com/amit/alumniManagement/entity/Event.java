package com.amit.alumniManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    private String eventName;

    @Column(columnDefinition = "TEXT")
    private String eventDescription;

    private String eventLocation;
    private LocalDateTime eventDateTime;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
