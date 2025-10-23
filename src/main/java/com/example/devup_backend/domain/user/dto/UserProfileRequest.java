package com.example.devup_backend.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UserProfileRequest {

    private String nickname;
    private List<String> fieldIds;

    @Builder
    public UserProfileRequest(String nickname, List<String> fieldIds) {
        this.nickname = nickname;
        this.fieldIds = fieldIds;
    }
}
