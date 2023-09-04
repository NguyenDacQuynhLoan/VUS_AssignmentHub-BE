package com.edusystem.dto;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private boolean executionStatus;

    private T result;

    private String message;
}