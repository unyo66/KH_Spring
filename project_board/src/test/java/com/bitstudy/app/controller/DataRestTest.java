package com.bitstudy.app.controller;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
*   슬라이스 테스트 : 기능별(레이어별)로 잘라서 특정 부분(기능)만 테스트하는것
*
*   -통합 테스트 에너테이션
*   @SpringBootTest : 스프링이 관리하는 모든 빈을 등록시켜서 테스트 하기 때문에 무거움
*                       테스트를 가볍게 하기 위해서 @WebMvsTest를 사용해 web 레이어 관련 빈들만 등록한 상태로 테스트를 할 수도 있음
*                       단 web 레이어 관련 빈들만 등록되므로 Service 는 등록되지 않음. 그래서 Mock 관련 어노테이션을 이용해서 가짜로 만들어줘야 함
*
*   -슬라이스 테스트 에너테이션
*   1) @WebMvcTest : 슬라이스 테스트에서 대표적인 에너테이션
*                   Controller 를 테스트할 수 있도록 관련 설정을 제공해 줌.
*                   @WebMvcTest를 선언하면 web과 관련된 Bean만 주입되고, MockMvc를 알아볼 수 있게 됨.
*
*                   *MockMvc : 웹 어플리케이션을 서버에 배포하지 않고 가짜로 테스트용 MVC 환경을 만들어 요청, 전송, 응답기능 제공해주는 유틸리티 클래스
*
*   2) @DataJpaTest : JPA 레포지토리 테스트할때 사용
*                   @Entity가 있는 엔티티 클래스들을 스캔해서 테스트를 위한 JPA 레포지토리들을 설정
*                   @Component 나 @ConfigurationProperties, @Bean 은 무시
*
*   3) @RestClientText : (클라이언트 입장에서의) API 연동 테스트
*                       테스트 코드 내에서 Mock 서버를 띄울 수 있음. (response, request 에 대한 사전 정의가 가능)
*
* */

@WebMvcTest
public class DataRestTest {

    /*
    *   MockMvc 테스트 방법
    *   1) MockMvc 생성 (빈 준비)
    *   2) MockMvc 에게 요청에 대한 정보 주입
    *   3) 요청에 대한 응답값을 expect를 이용해 테스트
    *   4) 다 통과하면 테스트 통과
    * */

    private final MockMvc mvc; //1

    public DataRestTest(MockMvc mvc) { //2
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
