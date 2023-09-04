package com.edusystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.edusystem.services.ExceptionService;

/**
 * Exception Controller Handler
 */
public class ExceptionController {
    @ExceptionHandler(ExceptionService.class)
    public ResponseEntity<String> ExceptionHandler (ExceptionService exceptionService){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionService.getMessage());
    }
}