package com.bitstudy.app.domain;

import java.time.LocalDateTime;

public class Ex00_Comment {
    private Long id;

    private  Article article;
    /*  연관관계 매핑
    *   연관관계 없이 만들면 private Long article_id; 이런식으로(관계형 DB 스타일)
    *   그런데 여기서는 Article 과 Comment 가 관계를 맺고 있는걸 객체지향적으로 표현하기 위해 이렇게 씀.
    * */

    private String content;

    //메타데이터
    private LocalDateTime create_at;
    private String create_by;
    private LocalDateTime modify_at;
    private String modify_by;
}
