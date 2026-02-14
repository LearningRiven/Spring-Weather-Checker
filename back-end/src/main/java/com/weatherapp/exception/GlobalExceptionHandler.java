package com.weatherapp.exception;

import com.weatherapp.dto.response.ErrorResponseDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //Handle specific response 400 errors from the api
    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<ErrorResponseDTO> handleRestClientException(
            RestClientResponseException ex,
            WebRequest request) {
        int statusCode = ex.getStatusCode().value();
        String message = mapApiErrorMessage(statusCode);

        ErrorResponseDTO error = ErrorResponseDTO.of(message, statusCode);
        return new ResponseEntity<>(error, HttpStatusCode.valueOf(statusCode));
    }

    //Handle generic 400
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleConstraintViolationException(
            ConstraintViolationException ex,
            WebRequest request) {
        String message = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));

        ErrorResponseDTO error = ErrorResponseDTO.of(message, 400);
        return ResponseEntity.badRequest().body(error);
    }

    //fallback, something failed, must be a 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(
            Exception ex,
            WebRequest request) {
        // Add logging here later: log.error("Unhandled exception", ex);
        ErrorResponseDTO error = ErrorResponseDTO.of("An unexpected error occurred", 500);
        return ResponseEntity.internalServerError().body(error);
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
