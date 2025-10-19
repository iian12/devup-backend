package com.example.devup_backend.domain.user.service;

import com.example.devup_backend.domain.user.dto.UserProfileRequest;
import com.example.devup_backend.domain.user.dto.UserStatusResponse;
import com.example.devup_backend.domain.user.model.UserField;
import com.example.devup_backend.domain.user.model.UserStatus;
import com.example.devup_backend.domain.user.model.Users;
import com.example.devup_backend.domain.user.repository.FieldRepository;
import com.example.devup_backend.domain.user.repository.UserFieldRepository;
import com.example.devup_backend.domain.user.repository.UserRepository;
import com.example.devup_backend.global.auth.service.CustomUserDetail;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final FieldRepository fieldRepository;
    private final UserFieldRepository userFieldRepository;

    public UserService(UserRepository userRepository, FieldRepository fieldRepository, UserFieldRepository userFieldRepository) {
        this.userRepository = userRepository;
        this.fieldRepository = fieldRepository;
        this.userFieldRepository = userFieldRepository;
    }

    public void joinNewUser() {
        Users user = Users.builder()
                .build();
    }

    public UserStatusResponse getMyStatus(CustomUserDetail userDetail) {
        Users user = userDetail.getUser();

        UserStatus status = user.getStatus();
        boolean profileCompleted = user.isProfileCompleted();

        return new UserStatusResponse(status, profileCompleted);
    }

    public void saveUserNicknameAndField(CustomUserDetail userDetail, UserProfileRequest request) {
        Users user = userDetail.getUser();

        String nickname = request.getNickname();
        List<Long> fieldIds = request.getFieldIds();

        for (int i = 0; i < fieldIds.size() && i < 2; i++) {
            if (fieldIds.get(i) == null) {
                UserField userField = UserField.builder()
                        .userId(user.getId())
                        .isSkipped(true)
                        .priority(i + 1)
                        .fieldId(null)
                        .build();
                userFieldRepository.save(userField);
            } else {
                Long fieldId = fieldRepository.findById(fieldIds.get(i))
                        .orElseThrow(() -> new IllegalArgumentException("Invalid ID")).getId();

                UserField userField = UserField.builder()
                        .userId(user.getId())
                        .fieldId(fieldId)
                        .priority(i + 1)
                        .isSkipped(false)
                        .build();
                userFieldRepository.save(userField);
            }
        }

        user.updateNickname(nickname);
        user.updateStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }
}
