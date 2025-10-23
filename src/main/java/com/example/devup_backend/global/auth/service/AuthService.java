package com.example.devup_backend.global.auth.service;

import com.example.devup_backend.domain.user.model.Provider;
import com.example.devup_backend.domain.user.model.UserId;
import com.example.devup_backend.domain.user.model.Users;
import com.example.devup_backend.domain.user.repository.UserRepository;
import com.example.devup_backend.global.auth.dto.IdToken;
import com.example.devup_backend.global.auth.dto.LinkInfo;
import com.example.devup_backend.global.auth.dto.LinkToken;
import com.example.devup_backend.global.auth.dto.TokenResult;
import com.example.devup_backend.global.security.jwt.JwtRedisService;
import com.example.devup_backend.global.security.jwt.JwtService;
import com.example.devup_backend.global.security.jwt.JwtTokenProvider;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.util.*;


@Service
@Transactional
public class AuthService {

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtService jwtService;
    private final JwtRedisService jwtRedisService;

    public AuthService(JwtTokenProvider jwtTokenProvider, UserRepository userRepository, RedisTemplate<String, Object> redisTemplate, JwtService jwtService, JwtRedisService jwtRedisService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
        this.jwtService = jwtService;
        this.jwtRedisService = jwtRedisService;
    }

    @Value("${client-id}")
    private String CLIENT_ID;

    public TokenResult processingGoogleUser(IdToken idToken) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY)
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken token = verifier.verify(idToken.idToken());

            if (token == null) throw new RuntimeException("Invalid ID token");

            GoogleIdToken.Payload payload = token.getPayload();

            String email = payload.getEmail();
            String profileImgUrl = payload.get("picture").toString();
            String subjectId = payload.getSubject();

            Optional<Users> userOptional = userRepository.findByEmail(email);

            if (userOptional.isEmpty()) {
                Users user = Users.builder()
                        .email(email)
                        .provider(Provider.GOOGLE)
                        .subjectId(subjectId)
                        .profileImageUrl(profileImgUrl)
                        .build();

                userRepository.save(user);

                String accessToken = jwtTokenProvider.createAccessToken(user.getUserId());
                String refreshToken = createAndStoreRefreshToken(user.getUserId());

                return new TokenResult(
                        accessToken,
                        refreshToken,
                        null,
                        false);
            }

            Users existingUser = userOptional.get();

            if (existingUser.getProvider() == null) {
                String linkToken = UUID.randomUUID().toString();
                redisTemplate.opsForValue().set(
                        "linkToken:" + linkToken,
                        new LinkInfo(email, "GOOGLE", subjectId),
                        Duration.ofMinutes(5)
                );
                return new TokenResult(
                        null,
                        null,
                        linkToken,
                        existingUser.isProfileCompleted());
            }

            String accessToken = jwtTokenProvider.createAccessToken(existingUser.getUserId());
            String refreshToken = createAndStoreRefreshToken(existingUser.getUserId());

            return new TokenResult(
                    accessToken,
                    refreshToken,
                    null,
                    existingUser.isProfileCompleted());
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException("Google ID token verification failed", e);
        }
    }

    private String createAndStoreRefreshToken(UserId userId) {
        String refreshToken = jwtTokenProvider.createRefreshToken(userId);
        jwtRedisService.storeRefreshToken(userId, refreshToken, jwtService.getTokenId(refreshToken), jwtService.getRemainingTTL(refreshToken));

        return refreshToken;
    }

    public void linkGoogleAccount(LinkToken linkToken) {
        LinkInfo linkInfo = (LinkInfo) redisTemplate.opsForValue().get("linkToken:" + linkToken.linkToken());

        if (linkInfo == null) throw new RuntimeException("Invalid link token");

        Users existingUser = userRepository.findByEmail(linkInfo.email())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        existingUser.linkAccount(Provider.GOOGLE, linkInfo.subjectId());
        userRepository.save(existingUser);

        redisTemplate.delete("linkToken:" + linkToken.linkToken());
    }
}
