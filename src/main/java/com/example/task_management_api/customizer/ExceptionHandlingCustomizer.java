package com.example.task_management_api.customizer;

import com.example.task_management_api.handler.CustomAuthenticationEntryPointHandler;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;

public class ExceptionHandlingCustomizer implements Customizer<ExceptionHandlingConfigurer<HttpSecurity>> {
    @Override
    public void customize(ExceptionHandlingConfigurer<HttpSecurity> configurer) {
        configurer.authenticationEntryPoint(new CustomAuthenticationEntryPointHandler());
    }
}