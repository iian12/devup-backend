package com.example.devup_backend.domain.post.controller;

import com.example.devup_backend.domain.post.dto.PostCreateReqDto;
import com.example.devup_backend.domain.post.service.PostService;
import com.example.devup_backend.global.auth.service.CustomUserDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    /*
    @GetMapping("/{encodedPostId}/{slug}")
    public ResponseEntity<Void> getPost(@PathVariable @Base62Id Long encodedPostId, @PathVariable String slug) {

    }
    */

    @PostMapping("/new")
    public ResponseEntity<String> createPost(@RequestBody PostCreateReqDto reqDto, @AuthenticationPrincipal CustomUserDetail userDetail) {
        String postId = postService.createPost(userDetail, reqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(postId);
    }
}
