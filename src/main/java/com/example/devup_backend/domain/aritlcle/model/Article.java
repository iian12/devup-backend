package com.example.devup_backend.domain.aritlcle.model;

import com.example.devup_backend.domain.user.model.UserId;
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
public class Article {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "article_id"))
    private ArticleId articleId;

    @AttributeOverride(name = "value", column = @Column(name = "user_id"))
    private UserId userId;

    private String title;
    @Lob
    private String content;

    private List<Long> hashtagIds;
    private LocalDateTime createdAt;

    @Builder
    public Article(UserId userId, String title, String content, List<Long> hashtagIds) {
        this.articleId = ArticleId.newId();
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.hashtagIds = hashtagIds;
        this.createdAt = LocalDateTime.now();
    }
}
