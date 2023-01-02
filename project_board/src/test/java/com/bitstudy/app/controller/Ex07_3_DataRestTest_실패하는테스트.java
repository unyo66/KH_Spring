package com.bitstudy.app.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class Ex07_3_DataRestTest_실패하는테스트 {

    /*
    *   MockMvc 테스트 방법
    *   1) MockMvc 생성 (빈 준비)
    *   2) MockMvc 에게 요청에 대한 정보 주입
    *   3) 요청에 대한 응답값을 expect를 이용해 테스트
    *   4) 다 통과하면 테스트 통과
    * */

    private final MockMvc mvc; //1

    public Ex07_3_DataRestTest_실패하는테스트(MockMvc mvc) { //2
        this.mvc = mvc;
    }

    @DisplayName("[api] - 게시글 리스트 전체 조회")
    @Test
    void articlesAll() throws Exception { //3
        /*
        *   해당 api를 찾을 수 없기 때문에 테스트는 실패하는게 맞음.
        *   콘솔창에 MockHttpServletRequest 부분에 URI="/api/articles" 복사해서 http://localhost:8080/api/articles 넣어보면
        *   데이터가 제대로 나옴.
        *
        *   @WebMvcTest는 슬라이스 테스트기 때문에 Controller 외의 모듈은 로드하지 않기 때문.
        * */

        /*
        *   특수 import (딥다이브)
        *   control + space : deep dive (모든 제안 검색)
        *   option + enter : static import (필드나 메서드를 클래스 지정하지 않고 사용할 수 있게 하기)
        * */
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));

    }
}
