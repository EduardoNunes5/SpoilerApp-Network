package com.eduardo.spoilerappnetwork.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException e){
        return buildErrorMessage(HttpStatus.NOT_FOUND, e.getMessage(), Collections.singletonList(e.getMessage()));
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<Object> handleEntityExists(EntityExistsException e){
        return buildErrorMessage(HttpStatus.BAD_REQUEST ,e.getMessage(), Collections.singletonList(e.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getFieldErrors().stream()
                .map(fieldError -> "Field " + fieldError.getField().toUpperCase() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        return buildErrorMessage(HttpStatus.BAD_REQUEST, "malformed/invalid/missing request fields", errors);
    }

    private ResponseEntity<Object> buildErrorMessage(HttpStatus status, String msg, List<String> errors){
        CustomErrorMessage errorMessage = CustomErrorMessage.builder()
            .message(msg)
            .code(status.value())
            .status(status.getReasonPhrase())
            .errors(errors)
            .localDateTime(LocalDateTime.now())
            .build();
        return ResponseEntity.status(status).body(errorMessage);
    }
}
