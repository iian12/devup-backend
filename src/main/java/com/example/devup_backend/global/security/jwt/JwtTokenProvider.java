package com.example.devup_backend.global.security.jwt;

import com.example.devup_backend.domain.user.model.Users;
import com.example.devup_backend.domain.user.repository.UserRepository;
import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtTokenProvider {

    private final UserRepository userRepository;
    private final JwtKeyProvider jwtKeyProvider;
    private Key key;

    public JwtTokenProvider(UserRepository userRepository, JwtKeyProvider jwtKeyProvider) {
        this.userRepository = userRepository;
        this.jwtKeyProvider = jwtKeyProvider;
    }

    @PostConstruct
    public void init() {
        this.key = jwtKeyProvider.getKey();
    }

    @Value("${jwt.access-token-validity-in-ms}")
    private int accessTokenValidityInMs;
    @Value("${jwt.refresh-token-validity-in-ms}")
    private int refreshTokenValidityInMs;

    public String createAccessToken(Long userId) {
        try {
            if (userId == null) {
                throw new IllegalArgumentException("userId cannot be null");
            }

            Users user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

            Date now = new Date();
            Date validity = new Date(now.getTime() + accessTokenValidityInMs);

            return Jwts.builder()
                    .setSubject(userId.toString())
                    .claim("role", user.getRole().toString())
                    .setIssuedAt(now)
                    .setExpiration(validity)
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String createRefreshToken(Long userId) {
        try {
            Date now = new Date();
            Date validity = new Date(now.getTime() + refreshTokenValidityInMs);

            // 멀티 디바이스 지원
            String tokenId = UUID.randomUUID().toString();

            return Jwts.builder()
                    .setSubject(userId.toString())
                    .setId(tokenId)
                    .setIssuedAt(now)
                    .setExpiration(validity)
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
