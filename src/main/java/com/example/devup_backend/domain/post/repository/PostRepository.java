package com.example.devup_backend.domain.post.repository;

import com.example.devup_backend.domain.post.model.Post;
import com.example.devup_backend.domain.post.model.PostId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, PostId> {
}
