package com.ou.ailearning.entity;

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

    private String status; // PENDING, APPROVED

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