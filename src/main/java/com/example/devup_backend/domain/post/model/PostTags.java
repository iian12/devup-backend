package com.example.devup_backend.domain.post.model;

import com.example.devup_backend.global.utils.TsidCreator;
import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostTags {

    @EmbeddedId
    private PostTagId postTagId;

    private String name;

    @Builder
    public PostTags(String name) {
        this.postTagId = PostTagId.newId();
        this.name = name;
    }
}
