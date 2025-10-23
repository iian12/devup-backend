package com.example.devup_backend.domain.user.model.field;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Field {

    @EmbeddedId
    private FieldId fieldId;

    private String name;

    private Long parentId;

    @Builder
    public Field(FieldId fieldId, String name, Long parentId) {
        this.fieldId = fieldId;
        this.name = name;
        this.parentId = parentId;
    }
}
