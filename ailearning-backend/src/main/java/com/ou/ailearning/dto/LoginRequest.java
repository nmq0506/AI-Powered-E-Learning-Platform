package com.ou.ailearning.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(

        @NotBlank(message = "Username và Email không được để trống")
        String usernameOrEmail,

        @NotBlank(message = "Mật khẩu không được để trống")
        String password

) {}
