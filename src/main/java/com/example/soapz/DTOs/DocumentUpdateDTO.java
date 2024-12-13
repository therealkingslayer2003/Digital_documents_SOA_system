package com.example.soapz.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DocumentUpdateDTO {
    @NotNull(message = "new contents is required")
    private String content;
}
