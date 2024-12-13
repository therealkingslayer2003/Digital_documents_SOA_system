package com.example.soapz.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DocumentCreateDTO {
    @NotNull(message = "title is required")
    private String title;

    private String content;

    @NotNull(message = "typeId is required")
    private String typeId;
}
