package com.example.soapz.DTOs;

import jakarta.validation.constraints.NotNull;

public record DocumentUpdateDTO (
        @NotNull Integer id,
        @NotNull(message = "new contents is required")
        String content
) {
}
