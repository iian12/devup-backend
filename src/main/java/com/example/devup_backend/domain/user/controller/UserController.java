package com.example.devup_backend.domain.user.controller;

import com.example.devup_backend.domain.user.dto.UserProfileRequest;
import com.example.devup_backend.domain.user.dto.UserStatusResponse;
import com.example.devup_backend.domain.user.service.UserService;
import com.example.devup_backend.global.auth.service.CustomUserDetail;
import com.example.devup_backend.global.utils.Base62Id;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 사용자와 관련된 요청을 처리하는 컨트롤러
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserStatusResponse> getMyInfo(@AuthenticationPrincipal CustomUserDetail userDetail) {
        UserStatusResponse response = userService.getMyStatus(userDetail);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/profile/setup")
    public ResponseEntity<Void> setFirstInfo(@AuthenticationPrincipal CustomUserDetail userDetail, @RequestBody UserProfileRequest request) {
        userService.saveUserNicknameAndField(userDetail, request);
        return ResponseEntity.ok().build();
    }

    /*
    @GetMapping("/{encodedUserId}/{nickname}")
    public ResponseEntity<Void> getUserInfo(@PathVariable("encodedId") @Base62Id Long encodedUserId, @PathVariable String nickname) {

    }
    */
}
