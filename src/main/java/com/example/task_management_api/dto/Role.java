package com.example.task_management_api.dto;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    User;
    @Override
    public String getAuthority() {
        return name();
    }

}