package com.bitstudy.app.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

/*  할일 : Lombok 사용하기
*
*   순서
*   1) Lombok을 이용해서 클래스를 엔티티로 변경
*   2) getter / setter, toString 등의 Lombok 어노테이션 사용
*   3) 동등성, 동일성 비교할 수 있는 코드 넣기
* */

@Getter
@Setter
@Entity
public class Article {
    private Long id;
    private String title;
    private String content;
    private String hashtag;

    //메타데이터
    private LocalDateTime create_at;
    private String create_by;
    private LocalDateTime modify_at;
    private String modify_by;
}
