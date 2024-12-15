package com.example.soapz.controllers;

import jakarta.validation.ConstraintViolationException;
import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;
import java.sql.SQLException;
import java.util.Objects;

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler({SQLException.class, IllegalArgumentException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleException(Exception ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler({AccessDeniedException.class, AuthenticationException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ErrorResponse handleAccessDeniedException(Exception ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }

    @Builder
    private record ErrorResponse(
            HttpStatus status,
            String message,
            Integer code
    ) {
        private ErrorResponse {
            code = Objects.requireNonNullElse(code, status.value());
        }
    }
}
