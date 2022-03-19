package com.emosegue.intelligence_service.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiException {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime timestamp;
    private final HttpStatus status;
    private final int code;
    private final String message;

    public ApiException(LocalDateTime timestamp, HttpStatus status, int code, String message) {
        this.timestamp = timestamp;
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
