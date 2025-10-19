package com.example.devup_backend.domain.aritlcle.model;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @Tsid
    private Long id;

    private Long userId;

    private String title;
    @Lob
    private String content;

    private List<Long> hashtagIds;
    private LocalDateTime createdAt;

    @Builder
    public Article(Long userId, String title, String content, List<Long> hashtagIds) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.hashtagIds = hashtagIds;
        this.createdAt = LocalDateTime.now();
    }
}
