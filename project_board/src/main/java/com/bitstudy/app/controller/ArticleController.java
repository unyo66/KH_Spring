package com.bitstudy.app.controller;

import com.bitstudy.app.domain.type.SearchType;
import com.bitstudy.app.dto.response.ArticleResponse;
import com.bitstudy.app.dto.response.ArticleWithCommentsResponse;
import com.bitstudy.app.service.ArticleService;
import com.bitstudy.app.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 뷰 엔드 포인트 관련 컨트롤러
 *
 * /articles                    GET 게시판 페이지
 * /articles/{articleId}       GET 게시글 페이지
 * /articles/search             GET 게시판 검색 전용 페이지
 * /articles/search_hashtag     GET 게시판 해시태그 검색 전용 페이지
 *
 * thymeleaf : 뷰 파일은 HTML로 작업할건데, 타임리프를 설치함으로써 스프링은 이제 html을 마크업이 아닌 타임리프 템플릿 파일로 인식함.
 * 그래서 이 html 파일들을 아무데서나 작성할 수 없고, resources/templates 폴더 안에만 생성 가능함.
 * */

@RequiredArgsConstructor // 필수 필드에 대한 생성자 자동 생성
// 초기화 되지 않은 final 필드 또는 @NonNull 이 붙은 필드에 대해 생성자 생성해주는 롬복 에너테이션
@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;


    /* @RequestParam: 검색어를 받아야 한다. @RequestParam 를 이용해서 getParameter 를 불러옴.
        없으면 게시글 전체 조회하면 되니까  (required = false) 달아서 null 들어올 수 있게 함.
        @PageableDefault: 페이징 기본설정 (한페이지에 10개, 작성일 순, 내림차순-최근)
     */


    /* 페이징 서비스 */
    private final PaginationService paginationService;

    @GetMapping
    public String articles(@RequestParam(required = false) SearchType searchType,
                           @RequestParam(required = false) String searchKeyword,
                           @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                           ModelMap map) {
//        map.addAttribute("articles", articleService.searchArticles(searchType, searchKeyword, pageable).map(ArticleResponse::from));

        Page<ArticleResponse> articles = articleService.searchArticles(searchType,searchKeyword,pageable).map(ArticleResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());
        map.addAttribute("articles", articles);
        map.addAttribute("paginationBarNumbers", barNumbers);

        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, ModelMap map) {
        ArticleWithCommentsResponse article = ArticleWithCommentsResponse.from(articleService.getArticle(articleId));
        map.addAttribute("article", article);
        map.addAttribute("comments", article.commentResponses());
        return "articles/detail";
    }
}
