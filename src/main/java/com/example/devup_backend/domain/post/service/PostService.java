package com.example.devup_backend.domain.post.service;

import com.example.devup_backend.domain.post.dto.PostCreateReqDto;
import com.example.devup_backend.domain.post.mapper.PostMapper;
import com.example.devup_backend.domain.post.model.Post;
import com.example.devup_backend.domain.post.model.PostTags;
import com.example.devup_backend.domain.post.repository.PostRepository;
import com.example.devup_backend.domain.post.repository.PostTagRepository;
import com.example.devup_backend.global.auth.service.CustomUserDetail;
import com.example.devup_backend.global.utils.SlugUtil;
import com.example.devup_backend.global.utils.TsidBase62Util;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;
    private final PostMapper postMapper;
    private final TsidBase62Util tsidBase62Util;

    public PostService(PostRepository postRepository, PostTagRepository postTagRepository, PostMapper postMapper,
                       TsidBase62Util tsidBase62Util) {
        this.postRepository = postRepository;
        this.postTagRepository = postTagRepository;
        this.postMapper = postMapper;
        this.tsidBase62Util = tsidBase62Util;
    }

    public String createPost(CustomUserDetail userDetail, PostCreateReqDto reqDto) {
        List<Long> postTagIds = new ArrayList<>();

        for (String name : reqDto.postTagNames()) {
            postTagIds.add(findOrCreatePostTag(name));
        }

        String slug = SlugUtil.slugify(reqDto.title());

        Post postEntity = postMapper.toEntity(reqDto, userDetail.getUser().getId(), postTagIds, slug);
        Post savedPost = postRepository.save(postEntity);

        postRepository.save(savedPost);
        return tsidBase62Util.encode(savedPost.getId());
    }

    private Long findOrCreatePostTag(String name) {
        return postTagRepository.findByName(name)
                .map(PostTags::getId)
                .orElseGet(() -> {
                    PostTags newTag = PostTags.builder().name(name).build();
                    postTagRepository.save(newTag);
                    return newTag.getId();
                });
    }
}
