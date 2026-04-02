package com.ou.ailearning.entity;

import com.ou.ailearning.entity.enums.CourseStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private CourseStatus status; // PENDING, APPROVED, REJECTED

    private Instant createdAt;
    private Long instructorId;
    private Boolean active;
    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
        if (this.active == null) {
            this.active = true;
        }
    }
}