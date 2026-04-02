package com.ou.ailearning.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String videoUrl;

    private String documentUrl;

    private Integer lessonOrder;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private Instant createdAt;

    private Boolean active;
    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
        if (this.active == null) {
            this.active = true;
        }
    }
}
