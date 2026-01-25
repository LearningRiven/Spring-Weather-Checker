package com.weatherapp.exception;

import com.weatherapp.dto.response.ErrorResponseDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;

import java.util.stream.Collectors;

@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    //API errors
    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<ErrorResponseDTO> handleRestClientException(RestClientResponseException e){
        int statusCode = e.getStatusCode().value();
        String message = mapApiErrorMessage(statusCode);

        return ResponseEntity
                .status(statusCode)
                .body(ErrorResponseDTO.of(message, statusCode));
    }

    //Bad input
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(ConstraintViolationException e) {
        String message = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDTO.of(message, 400));
    }

    //Fallback
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponseDTO.of("An unexpected error occurred", 500));
    }

    private String mapApiErrorMessage(int statusCode) {
        return switch (statusCode) {
            case 404 -> "Location not found. Please check your zip code and country.";
            case 401 -> "API authentication failed. Please contact support.";
            case 429 -> "Too many requests. Please try again later.";
            default -> "Weather service unavailable. Please try again.";
        };
    }
}
