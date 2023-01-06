package com.bitstudy.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
@Controller
@RequestMapping("/articles")
public class ArticleController {
    @GetMapping
    public String articles(ModelMap map) {
        map.addAttribute("articles", List.of());
        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, ModelMap map) {
        map.addAttribute("article", null); // 지금 당장은 받아오지 않기 때문에 null 이라고 넣었지만, 테스트할때에는 뭐라도 문자열을 넣줘서 모델에 담기도록 한다.
        map.addAttribute("articleComments", List.of());
        return "articles/detail";
    }
}
