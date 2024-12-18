package com.crud.crud_app.exceptions;

import java.util.List;

public class ApiErrorException extends RuntimeException {
    private final ApiError apiError;

    public ApiErrorException(String message, int status) {
        super(message);
        this.apiError = new ApiError(message, status);
    }

    public ApiErrorException(String message, int status, List<String> details) {
        super(message);
        this.apiError = new ApiError(message, status, details);
    }
    
    public ApiError getApiError() {
        return apiError;
    }
}
