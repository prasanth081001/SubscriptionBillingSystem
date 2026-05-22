package com.example.SubscriptionBillingSystem.Exception;

import com.example.SubscriptionBillingSystem.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>>
    handleResourceNotFound(
            ResourceNotFoundException ex
    ) {

        ApiResponse<String> response =
                ApiResponse.<String>builder()
                        .status(false)
                        .message(ex.getMessage())
                        .data(null)
                        .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>>
    handleGlobalException(
            Exception ex
    ) {

        ApiResponse<String> response =
                ApiResponse.<String>builder()
                        .status(false)
                        .message(ex.getMessage())
                        .data(null)
                        .build();

        return new ResponseEntity<>(
                response,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
