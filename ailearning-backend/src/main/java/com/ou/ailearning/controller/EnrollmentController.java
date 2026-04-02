package com.ou.ailearning.controller;

import com.ou.ailearning.dto.request.EnrollmentRequest;
import com.ou.ailearning.dto.response.ApiResponse;
import com.ou.ailearning.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Enrollment", description = "Đăng ký khóa học")
@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }
    @Operation(summary = "Đăng ký khóa học mới")
    @PostMapping
    public ResponseEntity<ApiResponse> enroll(
            @RequestBody @Valid EnrollmentRequest request,
            Authentication authentication
    ) {
        enrollmentService.enroll(request, authentication.getName());
        return ResponseEntity.ok(new ApiResponse("Bạn đã đăng ký khóa học thành công"));
    }
}
