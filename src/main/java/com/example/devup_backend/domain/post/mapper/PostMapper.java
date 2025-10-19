package com.example.devup_backend.domain.post.mapper;

import com.example.devup_backend.domain.post.dto.PostCreateReqDto;
import com.example.devup_backend.domain.post.model.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PostMapper {

    public Post toEntity(PostCreateReqDto dto,
                         Long userId,
                         List<Long> hashtagIds,
                         String slug) {
        return Post.builder()
                .title(dto.title())
                .content(dto.content())
                .boardType(dto.boardType())
                .slug(slug)
                .hashtagIds(hashtagIds)
                .userId(userId)
                .build();
    }
}
