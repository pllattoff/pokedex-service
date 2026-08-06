package org.example.pokedexservice.exception;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public record ErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message,
        Map<String, List<String>> details
) {
    public static ErrorResponse createGenericErrorResponse(HttpStatus status, String message) {
        return new ErrorResponse(Instant.now(), status.value(), status.name(), message, null);
    }

    public static ErrorResponse createValidationErrorResponse(HttpStatus status, String message, Map<String, List<String>> details) {
        return new ErrorResponse(Instant.now(), status.value(), status.name(), message, details);
    }

}
