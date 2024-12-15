package com.example.soapz.DTOs;

import jakarta.validation.constraints.NotNull;

public record DocumentCreateDTO(
        @NotNull(message = "title is required")
        String title,

        String content,

        @NotNull(message = "typeId is required") String typeId
) {
}
