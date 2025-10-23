package com.example.devup_backend.domain.user.model.field;

import com.example.devup_backend.domain.user.model.UserId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserField {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "user_field_id"))
    private UserFieldId userFieldId;

    @AttributeOverride(name = "value", column = @Column(name = "user_id"))
    private UserId userId;

    @AttributeOverride(name = "value", column = @Column(name = "field_id"))
    private FieldId fieldId;

    private Boolean isSkipped;

    private Integer priority;

    @Builder
    public UserField(UserId userId, FieldId fieldId, Boolean isSkipped, Integer priority) {
        this.userFieldId = UserFieldId.newId();
        this.userId = userId;
        this.fieldId = fieldId;
        this.isSkipped = isSkipped;
        this.priority = priority;
    }
}
