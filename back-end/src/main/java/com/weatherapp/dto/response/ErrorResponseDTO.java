package com.weatherapp.dto.response;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        String message,
        int status,
        LocalDateTime timestamp
) {
    // Factory method for convenience
    public static ErrorResponseDTO of(String message, int status) {
        return new ErrorResponseDTO(message, status, LocalDateTime.now());
    }
}
