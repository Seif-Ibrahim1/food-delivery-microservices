package com.fooddelivery.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        // Return 404 NOT FOUND instead of 500 INTERNAL SERVER ERROR
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}