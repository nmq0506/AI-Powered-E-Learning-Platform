package com.ou.ailearning.entity;

import com.ou.ailearning.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // STUDENT, INSTRUCTOR, ADMIN

    private String avatar;

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