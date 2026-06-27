package com.example.formpractice.controller.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.formpractice.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(IllegalArgumentException.class)    
    public ResponseEntity<ApiResponse> handleIllegalExeption(IllegalArgumentException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("errorMessage", e.getMessage());
        return ResponseEntity.badRequest().body(new ApiResponse("エラーが発生しました", errors));
    }
}
