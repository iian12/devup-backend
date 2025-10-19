package com.example.devup_backend.domain.user.dto;

import com.example.devup_backend.domain.user.model.UserStatus;

public record UserStatusResponse(UserStatus status, boolean profileCompleted) {}
