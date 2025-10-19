package com.example.devup_backend.global.auth.dto;

public record TokenResult(String accessToken, String refreshToken, String linkToken, boolean isProfileCompleted) {
}
