package com.bitstudy.app.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/** 이 테스트 코드를 작성하고 돌리면 404에러남.
 *  이유 : 아직 ArticleController 에 내용이 없고 Dao 같은것도 없기 때문.
 *
 *  우선 작성하고 실제 코드와 연결되는지 확인.
 * */

//@WebMvcTest //모든 컨트롤러를 다 읽어들임
@WebMvcTest(ArticleController.class) //지정된 컨트롤러만 읽어들임
class ArticleControllerTest {
    private final MockMvc mvc;

    public ArticleControllerTest(@Autowired MockMvc mvc){
        this.mvc = mvc;
    }

    /**1) 게시글 전체 테스트*/
    @Test
    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상호출")
    public void articlesAll() throws Exception {
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
    }

    /**2) 게시글 상세 테스트*/
    @Test
    @DisplayName("[view][GET] 게시글 상세 페이지 - 정상호출")
    public void articleOne() throws Exception {
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(model().attributeExists("article"))
                .andExpect(model().attributeExists("comments"));
    }

    /**3) 해시태그 검색 테스트*/
    @Test
    @DisplayName("[view][GET] 게시글 상세 페이지 - 정상호출")
    public void hashtag() throws Exception {
        mvc.perform(get("/articles/search_hashtag"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/search_hashtag"));
    }
}