package com.example.devup_backend.global.auth.controller;

import com.example.devup_backend.global.auth.dto.IdToken;
import com.example.devup_backend.global.auth.dto.TokenResult;
import com.example.devup_backend.global.auth.service.AuthService;
import com.example.devup_backend.global.auth.dto.LinkToken;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login/google")
    public ResponseEntity<Void> loginWithGoogle(@RequestBody IdToken idToken,
                                                @RequestHeader(value = "Platform") String platform,
                                                HttpServletResponse response) {
        TokenResult tokenResult = authService.processingGoogleUser(idToken);

        if ("WEB".equalsIgnoreCase(platform)) {
            Cookie accessCookie = new Cookie("access_token", tokenResult.accessToken());
            accessCookie.setHttpOnly(false);
            accessCookie.setSecure(false);
            accessCookie.setPath("/");
            accessCookie.setMaxAge(60 * 60 * 24);

            Cookie refreshCookie = new Cookie("refresh_token", tokenResult.refreshToken());
            refreshCookie.setHttpOnly(false);
            refreshCookie.setSecure(false);
            refreshCookie.setPath("/");
            refreshCookie.setMaxAge(60 * 60 * 24 * 7);

            response.addCookie(accessCookie);
            response.addCookie(refreshCookie);

            return ResponseEntity.ok().build();
        }

        response.setHeader("Authorization", "Bearer " + tokenResult.accessToken());
        response.setHeader("Refresh-Token", tokenResult.refreshToken());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/link-google")
    public ResponseEntity<Void> linkGoogle(@RequestBody LinkToken linkToken) {
        authService.linkGoogleAccount(linkToken);
        return ResponseEntity.ok().build();
    }
}
