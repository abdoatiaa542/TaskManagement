package com.example.task_management_api.repository;

import com.example.task_management_api.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessTokenRepository extends JpaRepository<AccessToken, String> {
}
