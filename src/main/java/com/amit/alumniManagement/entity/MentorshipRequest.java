package com.amit.alumniManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
// THE SPAM BLOCKER: This line tells DB "Student + Alumni pair must be unique"
@Table(name = "mentorship_requests", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"student_id", "alumni_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MentorshipRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id",  nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "alumni_id", nullable = false)
    private User alumni;

    @Column(columnDefinition = "TEXT") // allow to write long MSG
    private String message;

    @Enumerated(EnumType.STRING)
    private Status status;

    @CreationTimestamp
    private LocalDateTime createdAt;
}























