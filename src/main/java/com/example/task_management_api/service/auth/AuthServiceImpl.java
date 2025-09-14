package com.example.task_management_api.service.auth;

import com.example.task_management_api.dto.*;
import com.example.task_management_api.exception.ResourceAlreadyExistsException;
import com.example.task_management_api.model.AccessToken;
import com.example.task_management_api.model.User;
import com.example.task_management_api.repository.UserRepository;
import com.example.task_management_api.service.access_token.AccessTokenService;
import com.example.task_management_api.service.jwt.BearerTokenWrapper;
import com.example.task_management_api.utils.ContextHolderUtils;
import io.jsonwebtoken.lang.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final AccessTokenService accessTokenService;
    private final BearerTokenWrapper bearerTokenWrapper;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Object loginUser(Object object) {
        try {
            LoginRequest request = (LoginRequest) object;
            Authentication authentication = authenticationManager.
                    authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

            if (Objects.nonNull(authentication) && authentication.isAuthenticated() && authentication.getPrincipal() instanceof User user) {
                AccessToken accessToken = accessTokenService.create(user);
                AccessToken refreshToken = accessTokenService.refresh(user);
                userRepository.save(user);


                String token = accessToken.getToken();
                String refreshTokenValue = refreshToken.getToken();
                String message = "Successful user login.";


                LoginResponse response = new LoginResponse(
                        token,
                        refreshTokenValue
                );

                return ApiResponse.of(message, response);
            } else {
                return ApiResponse.of("Invalid username or password");
            }
        } catch (Exception e) {
            return ApiResponse.of("Error logging in user: " + e.getMessage());
        }
    }

    @Override
    public Object logoutUser(String deviceToken) {
        String bearerToken = bearerTokenWrapper.getToken();
        if (!Strings.hasText(bearerToken)) {
            throw new IllegalArgumentException("Bearer token must not be null or empty");
        }
        accessTokenService.delete(bearerToken);

        if (Strings.hasText(deviceToken)) {
            User currentUser = ContextHolderUtils.getUser();
            userRepository.save(currentUser);
        }

        return ApiResponse.of("User logged out successfully.");
    }

    @Override
    public ApiResponse registerUser(RegistrationRequest request) {
        if (userRepository.existsByEmailIgnoreCase(request.email())) {
            throw new ResourceAlreadyExistsException("User with this email already exists!");
        }

        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .username(request.username())
                .role(Role.User)
                .build();

        userRepository.save(user);
        String message = "User registered successfully.";

        return ApiResponse.of(message);

    }
}
