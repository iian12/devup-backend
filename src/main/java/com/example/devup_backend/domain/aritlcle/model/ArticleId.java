package com.example.devup_backend.domain.aritlcle.model;

import com.example.devup_backend.global.utils.TsidCreator;
import jakarta.persistence.Embeddable;

@Embeddable
public record ArticleId(Long value) {

    public static ArticleId of(Long value) {
        return new ArticleId(value);
    }

    public static ArticleId newId() {
        return new ArticleId(TsidCreator.create());
    }
}
