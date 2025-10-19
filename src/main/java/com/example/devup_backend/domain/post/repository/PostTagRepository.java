package com.example.devup_backend.domain.post.repository;

import com.example.devup_backend.domain.post.model.PostTags;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostTagRepository extends JpaRepository<PostTags, Long> {
    Optional<PostTags> findByName(String name);
}
