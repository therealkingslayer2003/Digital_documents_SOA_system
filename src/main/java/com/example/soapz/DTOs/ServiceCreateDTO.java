package com.example.soapz.DTOs;

import jakarta.validation.constraints.NotNull;

public record ServiceCreateDTO(

        @NotNull(message = "name is required")
        String name,

        @NotNull(message = "description is required")
        String description,

        @NotNull(message = "url is required")
        String url,

        @NotNull(message = "version is required")
        String version,

        @NotNull(message = "categoryId is required")
        Integer categoryId
) {
}
