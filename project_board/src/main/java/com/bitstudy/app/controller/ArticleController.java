package com.bitstudy.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 뷰 엔드 포인트 관련 컨트롤러
 *
 * /articles                    GET 게시판 페이지
 * /articles/{article_id}       GET 게시글 페이지
 * /articles/search             GET 게시판 검색 전용 페이지
 * /articles/search_hashtag     GET 게시판 해시태그 검색 전용 페이지
 *
 * thymeleaf : 뷰 파일은 HTML로 작업할건데, 타임리프를 설치함으로써 스프링은 이제 html을 마크업이 아닌 타임리프 템플릿 파일로 인식함.
 * 그래서 이 html 파일들을 아무데서나 작성할 수 없고, resources/templates 폴더 안에만 생성 가능함.
 * */
@RequestMapping("/articles")
public class ArticleController {
    //BDD 하러가기
}
