package com.ou.ailearning.dto.request;

import com.ou.ailearning.entity.enums.Role;

public record UpdateUserRequest(
        String email,
        String password,
        Role role,
        String avatar,
        Boolean active
) {
}