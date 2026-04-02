package com.ou.ailearning.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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
