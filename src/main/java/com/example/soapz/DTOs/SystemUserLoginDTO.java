package com.example.soapz.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record SystemUserLoginDTO(
        @Email
        @NotNull(message = "email is required")
        String email,

        @NotNull(message = "password is required")
        String password
) {
}
