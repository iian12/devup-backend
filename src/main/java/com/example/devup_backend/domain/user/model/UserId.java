package com.example.devup_backend.domain.user.model;

import com.example.devup_backend.global.utils.TsidCreator;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public record UserId(Long value) implements Serializable {

    public static UserId of(Long value) {
        return new UserId(value);
    }

    public static UserId newId() {
        return new UserId(TsidCreator.create());
    }
}
