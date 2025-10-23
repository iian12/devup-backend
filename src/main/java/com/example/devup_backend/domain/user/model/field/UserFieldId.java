package com.example.devup_backend.domain.user.model.field;

import com.example.devup_backend.global.utils.TsidCreator;
import jakarta.persistence.Embeddable;

@Embeddable
public record UserFieldId(Long value) {

    public static UserFieldId of(Long value) {
        return new UserFieldId(value);
    }

    public static UserFieldId newId() {
        return new UserFieldId(TsidCreator.create());
    }
}
