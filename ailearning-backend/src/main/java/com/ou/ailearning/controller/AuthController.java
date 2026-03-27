package com.ou.ailearning.controller;

import com.ou.ailearning.dto.AuthResponse;
import com.ou.ailearning.dto.LoginRequest;
import com.ou.ailearning.dto.RegisterRequest;
import com.ou.ailearning.dto.UserResponse;
import com.ou.ailearning.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Đăng nhập, đăng ký, thông tin user hiện tại")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @Operation(summary = "Đăng ký user mới")
    @PostMapping("register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse response = authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @Operation(summary = "Đăng nhập, nhận JWT")
    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse auth = authService.login(request);

        return ResponseEntity.ok(auth);
    }
    @Operation(summary = "Thông tin user hiện tại")
    @GetMapping("me")
    public ResponseEntity<UserResponse> me(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserResponse user = authService.getCurrentUser(userDetails.getUsername());
        return ResponseEntity.ok(user);
    }
}
