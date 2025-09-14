package com.example.task_management_api.dto;

public record RegistrationRequest(
        String email,
        String username,
        String password
) {

}
