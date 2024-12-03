package com.example.soapz.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SystemUserCreateDTO {
    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "surname is required")
    private String surname;

    @NotNull(message = "password is required")
    private String password;

    @NotNull(message = "email is required")
    @Email
    private String email;

    @NotNull(message = "name is required")
    private Integer roleId;
}
