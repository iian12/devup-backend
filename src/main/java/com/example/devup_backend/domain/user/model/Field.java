package com.example.devup_backend.domain.user.model;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Field {

    @Id
    @Tsid
    private Long id;

    private String name;

    private Long parentId;

    @Builder
    public Field(String name, Long parentId) {
        this.name = name;
        this.parentId = parentId;
    }
}
