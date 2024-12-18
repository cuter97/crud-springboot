package com.crud.crud_app.exceptions;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private String message;
    private int status;
    private List<String> details;

    public ApiError(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
