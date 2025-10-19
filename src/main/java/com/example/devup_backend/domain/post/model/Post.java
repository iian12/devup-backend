package com.example.devup_backend.domain.post.model;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @Tsid
    private Long id;

    private Long userId;
    private String title;
    @Lob
    private String content;
    private String slug;
    private BoardType boardType;

    @ElementCollection
    private List<Long> hashtagIds;

    private LocalDateTime createdAt;

    @Builder
    public Post(Long userId, String title, String content, String slug, BoardType boardType, List<Long> hashtagIds) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.slug = slug;
        this.boardType = boardType;
        this.hashtagIds = hashtagIds;
        this.createdAt = LocalDateTime.now();
    }

    public void updateContent(String title, String content, List<Long> hashtagIds) {
        this.title = title;
        this.content = content;
        this.hashtagIds = hashtagIds;
    }

    public void updateSlug(String newSlug) {
        this.slug = newSlug;
    }

}
