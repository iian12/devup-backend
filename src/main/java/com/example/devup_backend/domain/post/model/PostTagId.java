package com.example.devup_backend.domain.post.model;

import com.example.devup_backend.global.utils.TsidCreator;
import jakarta.persistence.Embeddable;

@Embeddable
public record PostTagId(Long value) {

    public static PostTagId of(Long value) {
        return new PostTagId(value);
    }

    public static PostTagId newId() {
        return new PostTagId(TsidCreator.create());
    }
}
