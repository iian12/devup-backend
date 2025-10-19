package com.example.devup_backend.domain.post.dto;

import com.example.devup_backend.domain.post.model.BoardType;

import java.util.List;

public record PostCreateReqDto(
        String title,
        String content,
        BoardType boardType,
        List<String> postTagNames
) {}