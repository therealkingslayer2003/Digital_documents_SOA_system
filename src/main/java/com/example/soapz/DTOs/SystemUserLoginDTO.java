package com.example.soapz.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SystemUserLoginDTO {
    @Email
    @NotNull(message = "email is required")
    private String email;
    @NotNull(message = "password is required")
    private String password;
}
