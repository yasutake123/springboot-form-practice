package com.example.formpractice.dto;

import java.util.Map;

public class ApiResponse {
    private boolean success;
    private String message;
    private Object UsersData;
    private Map<String, String> errors;

    public ApiResponse(String message, Object data) {
        this.success = true;
        this.message = message;
        this.UsersData = data;
        }

    public ApiResponse (String message, Map<String, String> errors) {
        this.success = false;
        this.message = message;
        this.errors = errors;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Object getData() { return UsersData; }
    public Map<String,String> getErrors() { return errors; }
}
