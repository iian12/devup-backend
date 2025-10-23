package com.example.devup_backend.domain.user.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "user_id"))
    private UserId userId;

    private String nickname;
    @Column(unique = true)
    private String email;
    private String password;
    private String bio;
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private boolean isProfileCompleted;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    private String subjectId;

    @Builder
    public Users(String nickname, String email, String password, String bio, String profileImageUrl, Provider provider, String subjectId) {
        this.userId = UserId.newId();
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.bio = bio;
        this.profileImageUrl = profileImageUrl;
        this.role = Role.USER;
        this.status = UserStatus.INACTIVE;
        this.isProfileCompleted = false;
        this.provider = provider;
        this.subjectId = subjectId;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateStatus(UserStatus status) {
        this.status = status;
    }

    public void updateProfileCompleted() {
        this.isProfileCompleted = true;
    }

    public void linkAccount(Provider provider, String subjectId) {
        this.provider = provider;
        this.subjectId = subjectId;
    }
}
