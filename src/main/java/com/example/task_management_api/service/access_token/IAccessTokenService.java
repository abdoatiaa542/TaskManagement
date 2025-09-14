package com.example.task_management_api.service.access_token;

import com.example.task_management_api.model.AccessToken;

public interface IAccessTokenService {
    AccessToken create(com.example.task_management_api.model.User user);

    AccessToken get(String token);

    boolean exists(String token);

    void delete(String token);

    AccessToken refresh(com.example.task_management_api.model.User user);
}