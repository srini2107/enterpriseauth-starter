package com.enterpriseauthstarter.auth.repository;

import com.enterpriseauthstarter.auth.entity.RefreshToken;
import com.enterpriseauthstarter.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository
        extends JpaRepository<RefreshToken, UUID> {

    Optional<RefreshToken> findByToken(String token);

    List<RefreshToken> findAllByUser(User user);
}
