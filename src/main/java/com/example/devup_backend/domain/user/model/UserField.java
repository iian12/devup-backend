package com.example.devup_backend.domain.user.model;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserField {

    @Id
    @Tsid
    private String id;

    private Long userId;

    private Long fieldId;

    private Boolean isSkipped;

    private Integer priority;

    @Builder
    public UserField(Long userId, Long fieldId, Boolean isSkipped, Integer priority) {
        this.userId = userId;
        this.fieldId = fieldId;
        this.isSkipped = isSkipped;
        this.priority = priority;
    }
}
