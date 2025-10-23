package com.example.devup_backend.domain.aritlcle.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hashtag {

    @EmbeddedId
    private HashtagId hashtagId;

    private String name;

    @Builder
    public Hashtag(String name) {
        this.hashtagId = HashtagId.newId();
        this.name = name;
    }
}
