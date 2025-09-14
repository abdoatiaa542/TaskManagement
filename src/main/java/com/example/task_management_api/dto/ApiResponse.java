package com.example.task_management_api.dto;

public record ApiResponse(
        boolean success,
        String message,
        Object data
) {

    public static ApiResponse of(String message, Object data) {
        return new ApiResponse(true, message, data);
    }

    public static ApiResponse of(String message) {
        return new ApiResponse(true, message, "");
    }


}