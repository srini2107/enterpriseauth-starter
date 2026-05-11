package com.enterpriseauthstarter.auth.service;

import com.enterpriseauthstarter.auth.entity.RefreshToken;
import com.enterpriseauthstarter.auth.entity.User;
import com.enterpriseauthstarter.auth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken createRefreshToken(User user) {

        List<RefreshToken> existingTokens =
                refreshTokenRepository.findAllByUser(user);

        existingTokens.forEach(token -> {
            token.setRevoked(true);
            refreshTokenRepository.save(token);
        });

        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiryDate(LocalDateTime.now().plusDays(7))
                .revoked(false)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    public boolean isValid(RefreshToken token) {

        return !token.isRevoked()
                && token.getExpiryDate().isAfter(LocalDateTime.now());
    }

    public void revokeToken(RefreshToken token) {
        token.setRevoked(true);
        refreshTokenRepository.save(token);
    }
}