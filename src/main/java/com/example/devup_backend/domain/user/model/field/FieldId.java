package com.example.devup_backend.domain.user.model.field;

import jakarta.persistence.Embeddable;

@Embeddable
public record FieldId(Long value) {

    public static FieldId of(Long value) {
        return new FieldId(value);
    }
}
