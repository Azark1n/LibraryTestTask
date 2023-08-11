package com.azarkin.librarytesttask.exception;

import lombok.Value;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Value
public class ApiError {
    String message;

    String reason;

    String status;

    LocalDateTime timestamp;

    public ApiError(String message, Exception exception, HttpStatus status) {
        this.message = message;
        this.reason = exception.getMessage();
        this.status = status.name();
        this.timestamp = LocalDateTime.now();
    }
}
