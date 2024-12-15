package com.example.soapz.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record SystemUserCreateDTO(
        @NotNull(message = "name is required") String name,
        @NotNull(message = "surname is required") String surname,
        @NotNull(message = "password is required") String password,
        @NotNull(message = "email is required") @Email String email,
        @NotNull(message = "name is required") Integer roleId
) {
}
