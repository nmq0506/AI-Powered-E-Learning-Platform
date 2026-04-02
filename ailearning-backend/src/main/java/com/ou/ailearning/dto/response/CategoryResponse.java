package com.ou.ailearning.dto.response;

import java.time.Instant;

public record CategoryResponse(
        Long id,
        String name,
        Instant createdAt
) {}