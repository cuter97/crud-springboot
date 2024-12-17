package com.crud.crud_app.exceptions;

public class ApiErrorException extends RuntimeException {
    private final ApiError apiError;

    public ApiErrorException(String message, int status) {
        super(message);
        this.apiError = new ApiError(message, status);
    }

    public ApiError getApiError() {
        return apiError;
    }
}
