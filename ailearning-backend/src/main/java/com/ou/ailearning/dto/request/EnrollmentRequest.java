package com.ou.ailearning.dto.request;

import jakarta.validation.constraints.NotNull;

public record EnrollmentRequest(
        @NotNull(message = "courseId không được null")
        Long courseId

) {}