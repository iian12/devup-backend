package com.example.devup_backend.domain.post.model;

import com.example.devup_backend.global.utils.TsidCreator;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public record PostId(Long value) implements Serializable {

    public static PostId of(Long value) {
        return new PostId(value);
    }

    public static PostId newId() {
        return new PostId(TsidCreator.create());
    }
}
