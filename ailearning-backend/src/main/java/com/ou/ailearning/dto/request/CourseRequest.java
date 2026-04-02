package com.ou.ailearning.dto.request;

public record CourseRequest(
        String title,
        String description,
        Long categoryId,
        Long instructorId
) {}