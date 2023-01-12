package com.bitstudy.app.controller;

import com.bitstudy.app.config.SecurityConfig;
import com.bitstudy.app.domain.type.SearchType;
import com.bitstudy.app.dto.ArticleWithCommentsDto;
import com.bitstudy.app.dto.CommentDto;
import com.bitstudy.app.dto.UserAccountDto;
import com.bitstudy.app.repository.ArticleRepository;
import com.bitstudy.app.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.bitstudy.app.service.PaginationService;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/** 이 테스트 코드를 작성하고 돌리면 404에러남.
 *  이유 : 아직 ArticleController 에 내용이 없고 Dao 같은것도 없기 때문.
 *
 *  우선 작성하고 실제 코드와 연결되는지 확인.
 * */

//@WebMvcTest //모든 컨트롤러를 다 읽어들임

@Import(SecurityConfig.class) // 테스트 코드에서도 SecurityConfig 가 있어야 시큐리티를 뚫고 들어갈 수 있음. 아니면 401 뜸
@WebMvcTest(ArticleController.class) //지정된 컨트롤러만 읽어들임
class ArticleControllerTest {
    private final MockMvc mvc;

    @MockBean // 실제 articleService 를 사용하지 않기 위해 진짜 객체 대신 테스트용 객체를 Bean 으로 등록
    private ArticleService articleService;
//    @Autowired
//    private ArticleRepository articleRepository;
    @MockBean
    private PaginationService paginationService;


    public ArticleControllerTest(@Autowired MockMvc mvc){
        this.mvc = mvc;
    }

    /**1) 게시글 전체 테스트*/
    @Test
    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상호출")
    public void articlesAll() throws Exception {
        given(articleService.searchArticles(eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());
        mvc.perform(get("/articles"))
                // 상태가 200이니?
                .andExpect(status().isOk())

                // articles 로 받아온 데이터의 미디어 타입이 html 타입으로 되어 있는지 확인
                // contentType 의 경우 exact match 라서 미디어 타입이 딱 text/html 로 나오는 것만 허용하기 때문에
                // contentTypeCompatibleWith 를 이용해서 호환되는 타입까지 맞다고 쳐주는거
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))


                //가져온 뷰 파일명이 index 인지 확인
                .andExpect(view().name("articles/index"))

                //이 뷰에서는 게시글들이 떠야 하는데, 그 말은 서버에서 데이터들을 가져와 모델에 넣었다는 뜻.
                // 모델에 키값이 articles 인 데이터가 있는지 확인
                .andExpect(model().attributeExists("articles"));
        then(articleService).should().searchArticles(eq(null), eq(null), any(Pageable.class));
    }

    /** 1.5) 게시글 전체 테스트 (검색어와) */
    @DisplayName("[view][GET] 게시글 리스트 검색어와 함께")
    @Test
    public void givenKeywordReturnArticles() throws Exception {

        //Given
        SearchType testSearchType = SearchType.TITLE;
        String testSearchKeyword = "title";
        /* Pageble 을 any 로 줬기 때문에 모든 매개변수를 matcher 로 줘야 함. 왜? */
        given(articleService.searchArticles(eq(testSearchType), eq(testSearchKeyword), any(Pageable.class))).willReturn(Page.empty());
        /* 검색 했을때 페이징 기능을 호출하는가만 보면 됨. */
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(1, 2, 3, 4));

        //When
        //Then
        mvc.perform(
                get("/articles")
                        .queryParam("searchType", testSearchType.name())
                        .queryParam("searchKeyword", testSearchKeyword))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index"))
                .andExpect(model().attributeExists("articles"))
                .andExpect(model().attributeExists("paginationBarNumbers"));
        then(articleService).should().searchArticles(eq(testSearchType), eq(testSearchKeyword), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }


    /**2) 게시글 상세 테스트*/
    @Test
    @DisplayName("[view][GET] 게시글 상세 페이지 - 정상호출")
    public void articleOne() throws Exception {

        Long articleId = 1L;
        long totalCount = 1L;
        given(articleService.getArticleWithComments(articleId)).willReturn(createArticleWithCommentsDto());
        given(articleService.getArticleCount()).willReturn(totalCount);

        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attributeExists("comments"));

        then(articleService).should().getArticleWithComments(articleId);
        then(articleService).should().getArticleCount();
    }

    ////////////////////////////////////////////

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "bitstudy",
                "asdf",
                "bitstudy@email.com",
                "bitstudy",
                "memomemo",
                LocalDateTime.now(),
                "bitstudy",
                LocalDateTime.now(),
                "bitstudy"
        );
    }
    private ArticleWithCommentsDto createArticleWithCommentsDto() {
        return ArticleWithCommentsDto.of(
                1L,
                createUserAccountDto(),
                Set.of(),
                "title",
                "content",
                "#java",
                LocalDateTime.now(),
                "bitstudy",
                LocalDateTime.now(),
                "bitstudy"
        );
    }
}