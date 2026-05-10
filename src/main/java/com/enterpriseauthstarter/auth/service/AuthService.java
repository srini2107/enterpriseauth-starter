package com.enterpriseauthstarter.auth.service;

import com.enterpriseauthstarter.auth.dto.*;
import com.enterpriseauthstarter.auth.entity.RefreshToken;
import com.enterpriseauthstarter.auth.entity.Role;
import com.enterpriseauthstarter.auth.entity.User;
import com.enterpriseauthstarter.auth.repository.RefreshTokenRepository;
import com.enterpriseauthstarter.auth.repository.UserRepository;
import com.enterpriseauthstarter.auth.security.JwtUtil;
import com.enterpriseauthstarter.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(user);

        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(jwtUtil.generateToken(user.getEmail()))
                .refreshToken(refreshToken.getToken())
                .build();
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new BadRequestException("Invalid credentials"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {

            throw new BadRequestException("Invalid credentials");
        }

        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user);

        return AuthResponse.builder()
                .accessToken(jwtUtil.generateToken(user.getEmail()))
                .refreshToken(refreshToken.getToken())
                .build();
    }

    public AuthResponse refreshToken(
            RefreshTokenRequest request
    ) {

        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(request.getRefreshToken())
                .orElseThrow(() ->
                        new BadRequestException("Invalid refresh token"));

        if (!refreshTokenService.isValid(refreshToken)) {
            throw new BadRequestException("Refresh token expired");
        }

        String accessToken =
                jwtUtil.generateToken(refreshToken.getUser().getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getToken())
                .build();
    }
}