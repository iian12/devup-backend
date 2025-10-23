package com.example.devup_backend.domain.aritlcle.model;

import com.example.devup_backend.global.utils.TsidCreator;
import jakarta.persistence.Embeddable;

@Embeddable
public record HashtagId(Long value) {

    public static HashtagId of(Long value) {
        return new HashtagId(value);
    }

    public static HashtagId newId() {
        return new HashtagId(TsidCreator.create());
    }
}
