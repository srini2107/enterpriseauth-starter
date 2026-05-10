package com.enterpriseauthstarter.auth.controller;

import com.enterpriseauthstarter.auth.dto.*;
import com.enterpriseauthstarter.auth.entity.RefreshToken;
import com.enterpriseauthstarter.auth.repository.RefreshTokenRepository;
import com.enterpriseauthstarter.auth.service.AuthService;
import com.enterpriseauthstarter.auth.service.RefreshTokenService;
import com.enterpriseauthstarter.exception.BadRequestException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final RefreshTokenService refreshTokenService;

    private final RefreshTokenRepository refreshTokenRepository;

    @PostMapping("/register")
    public AuthResponse register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @Valid @RequestBody LoginRequest request
    ) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public AuthResponse refreshToken(
            @RequestBody RefreshTokenRequest request
    ) {
        return authService.refreshToken(request);
    }

    @PostMapping("/logout")
    public String logout(
            @RequestBody RefreshTokenRequest request
    ) {

        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(request.getRefreshToken())
                .orElseThrow(() ->
                        new BadRequestException("Invalid refresh token"));

        refreshTokenService.revokeToken(refreshToken);

        return "Logged out successfully";
    }
}