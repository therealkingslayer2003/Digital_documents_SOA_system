package com.example.soapz.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ServiceCreateDTO {
    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "description is required")
    private String description;

    @NotNull(message = "url is required")
    private String url;

    @NotNull(message = "version is required")
    private String version;

    @NotNull(message = "categoryId is required")
    private Integer categoryId;
}
