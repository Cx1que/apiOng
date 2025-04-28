package br.com.unifecaf.service.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {
    private int status;
    private String message;
    private LocalDateTime timestamp;
    private String path;
    private List<FieldError> fieldErrors;

    public static class FieldError {
        private String field;
        private String error;

        public FieldError(String field, String error) {
            this.field = field;
            this.error = error;
        }

        public String getField() {
            return field;
        }

        public String getError() {
            return error;
        }
    }

    // Construtor completo
    public ApiError(int status, String message, String path, List<FieldError> fieldErrors) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
        this.fieldErrors = fieldErrors;
    }

    // Construtor sem fieldErrors
    public ApiError(int status, String message, String path) {
        this(status, message, path, null);
    }

    // Getters e Setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}