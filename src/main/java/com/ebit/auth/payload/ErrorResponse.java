package com.ebit.auth.payload;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private boolean success;
    private HttpStatus status;
    private String error;

    public ErrorResponse() {
    }

    public ErrorResponse(LocalDateTime timestamp, boolean success, HttpStatus status, String error) {
        this.timestamp = timestamp;
        this.success = success;
        this.status = status;
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
