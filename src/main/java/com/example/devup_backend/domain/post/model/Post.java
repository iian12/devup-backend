package com.example.devup_backend.domain.post.model;

import com.example.devup_backend.domain.user.model.UserId;
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

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "post_id"))
    private PostId postId;

    @AttributeOverride(name = "value", column = @Column(name = "user_id"))
    private UserId userId;

    private String title;

    @Lob
    private String content;
    private String slug;
    private BoardType boardType;

    @ElementCollection
    private List<Long> hashtagIds;

    private LocalDateTime createdAt;

    @Builder
    public Post(UserId userId, String title, String content, String slug, BoardType boardType, List<Long> hashtagIds) {
        this.postId = PostId.newId();
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
