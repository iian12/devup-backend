package com.example.devup_backend.domain.post.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PostResponseDto(
        String tsid,
        String title,
        String content,
        String authorId,
        String authorNickname,
        List<PostTagDto> postTag,
        LocalDateTime createdAt
) {
}
