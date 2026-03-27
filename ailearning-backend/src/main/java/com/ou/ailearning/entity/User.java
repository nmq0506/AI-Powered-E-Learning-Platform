package com.ou.ailearning.entity;

import com.ou.ailearning.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private Role role; // STUDENT, INSTRUCTOR, ADMIN

    private String avatar;

    private Instant createdAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public User(Long id, String username, String email, String password, Role role, String avatar, Instant createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.avatar = avatar;
        this.createdAt = createdAt;
    }

    public User() {

    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static final class UserBuilder {
        private Long id;
        private String username;
        private String email;
        private String password;
        private Role role;
        private String avatar;
        private Instant createdAt;

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder avatar(String avatar) {
            this.avatar = avatar;
            return this;
        }

        public UserBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public User build() {
            return new User(
                    id,
                    username,
                    email,
                    password,
                    role,
                    avatar,
                    createdAt
            );
        }
    }
}