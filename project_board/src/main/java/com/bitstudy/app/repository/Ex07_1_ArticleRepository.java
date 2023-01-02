package com.bitstudy.app.repository;

import com.bitstudy.app.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/*
* TDD를 위한 임시 저장소
*
* 할일 : 클래스에 @RepositoryRestResource 넣어서 해당 클래스를 spring rest data 사용할 준비 시켜놓기
* */
//@RepositoryRestResource
public interface Ex07_1_ArticleRepository extends JpaRepository<Article, Long> {
}
