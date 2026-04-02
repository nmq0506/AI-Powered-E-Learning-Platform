package com.ou.ailearning.dto.response;

import java.time.Instant;

public record CourseResponse(
        Long id,
        String title,
        String description,
        String categoryName,
        com.ou.ailearning.entity.enums.CourseStatus status,
        Boolean active,
        Instant createdAt,
        Long categoryId,
        Long instructorId
) {}