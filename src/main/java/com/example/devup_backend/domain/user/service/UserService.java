package com.example.devup_backend.domain.user.service;

import com.example.devup_backend.domain.user.dto.UserProfileRequest;
import com.example.devup_backend.domain.user.dto.UserStatusResponse;
import com.example.devup_backend.domain.user.model.*;
import com.example.devup_backend.domain.user.model.field.FieldId;
import com.example.devup_backend.domain.user.model.field.UserField;
import com.example.devup_backend.domain.user.repository.FieldRepository;
import com.example.devup_backend.domain.user.repository.UserFieldRepository;
import com.example.devup_backend.domain.user.repository.UserRepository;
import com.example.devup_backend.global.auth.service.CustomUserDetail;
import com.example.devup_backend.global.utils.TsidBase62Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * 사용자 관련 비즈니스 로직 처리
 */
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

    public UserStatusResponse getMyStatus(CustomUserDetail userDetail) {
        Users user = userDetail.getUser();

        UserStatus status = user.getStatus();
        boolean profileCompleted = user.isProfileCompleted();

        return new UserStatusResponse(status, profileCompleted);
    }

    /*
        * 사용자 닉네임 및 관심 분야 저장
     */
    public void saveUserNicknameAndField(CustomUserDetail userDetail, UserProfileRequest request) {
        Users user = userDetail.getUser();

        String nickname = request.getNickname();

        // Base62 인코딩된 String으로 받음
        List<String> fieldIdStrings = request.getFieldIds();

        for (int i = 0; i < fieldIdStrings.size() && i < 2; i++) {
            String fieldIdStr = fieldIdStrings.get(i);

            if (fieldIdStr == null || fieldIdStr.isBlank()) {
                UserField userField = UserField.builder()
                        .userId(user.getUserId())
                        .isSkipped(true)
                        .priority(i + 1)
                        .fieldId(null)
                        .build();
                userFieldRepository.save(userField);
            } else {
                // String → Long → FieldId
                long decodedId = TsidBase62Util.decode(fieldIdStr);
                FieldId fieldId = fieldRepository.findById(FieldId.of(decodedId))
                        .orElseThrow(() -> new IllegalArgumentException("Invalid ID"))
                        .getFieldId();

                UserField userField = UserField.builder()
                        .userId(user.getUserId())
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
