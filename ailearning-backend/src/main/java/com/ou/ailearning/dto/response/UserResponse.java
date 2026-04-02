package com.ou.ailearning.dto.response;

import com.ou.ailearning.entity.enums.Role;

import java.time.Instant;
import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String username,
        String email,
        Role role,
        String avatar,
        Instant createdAt
) {
}

