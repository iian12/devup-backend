package com.example.devup_backend.domain.post.model;

import io.hypersistence.utils.hibernate.id.Tsid;
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

    @Id
    @Tsid
    private Long id;

    private String name;

    @Builder
    public PostTags(String name) {
        this.name = name;
    }
}
