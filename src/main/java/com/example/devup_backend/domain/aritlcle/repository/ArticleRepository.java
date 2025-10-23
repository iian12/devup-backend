package com.example.devup_backend.domain.aritlcle.repository;

import com.example.devup_backend.domain.aritlcle.model.Article;
import com.example.devup_backend.domain.aritlcle.model.ArticleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, ArticleId> {
}
