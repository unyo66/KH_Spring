package com.bitstudy.app.repository;

import com.bitstudy.app.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/*
* TDD를 위한 임시 저장소
* */
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
