package com.example.task_management_api.service.jwt;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class BearerTokenWrapper {

    private String token;

}
