package com.edusystem.dto;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private boolean success;

    private T result;

    private String message;
}