package org.example.pokedexservice.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                            HttpHeaders headers,
                                                                            HttpStatusCode status,
                                                                            WebRequest request) {
        Map<String, List<String>> details = new LinkedHashMap<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String fieldName;
            if (error instanceof FieldError) {
                fieldName = ((FieldError) error).getField();
            } else {
                fieldName = error.getObjectName();
            }

            details.computeIfAbsent(fieldName, key -> new ArrayList<>())
                    .add(error.getDefaultMessage());
        }

        return buildValidationErrorResponse(HttpStatus.BAD_REQUEST, details);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, List<String>> details = new LinkedHashMap<>();

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String fieldName = extractFieldName(violation.getPropertyPath());
            details.computeIfAbsent(fieldName, key -> new ArrayList<>())
                    .add(violation.getMessage());
        }

        return buildValidationErrorResponse(HttpStatus.BAD_REQUEST, details);
    }

    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePokemonNotFoundException(PokemonNotFoundException ex) {
        return buildGenericErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException ex) {
        return buildGenericErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildGenericErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        return buildGenericErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private ResponseEntity<Object> buildValidationErrorResponse(HttpStatus status, Map<String, List<String>> details) {
        ErrorResponse body = ErrorResponse.createValidationErrorResponse(status, "Validation failed", details);
        return ResponseEntity.status(status).body(body);
    }

    private ResponseEntity<ErrorResponse> buildGenericErrorResponse(HttpStatus status, @Nullable String message) {
        ErrorResponse body = ErrorResponse.createGenericErrorResponse(status, message != null ? message : status.getReasonPhrase());
        return ResponseEntity.status(status).body(body);
    }

    private String extractFieldName(Path propertyPath) {
        String lastNode = null;
        for (Path.Node node : propertyPath) {
            lastNode = node.getName();
        }
        return lastNode != null ? lastNode : propertyPath.toString();
    }
}
