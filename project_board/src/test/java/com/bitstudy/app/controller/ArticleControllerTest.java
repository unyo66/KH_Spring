package com.bitstudy.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

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
}