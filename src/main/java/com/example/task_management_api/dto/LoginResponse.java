package com.example.task_management_api.dto;

public record LoginResponse(
    String token,
    String refreshToken
) {
}
