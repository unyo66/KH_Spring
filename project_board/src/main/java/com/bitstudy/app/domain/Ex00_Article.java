package com.bitstudy.app.domain;

import java.time.LocalDateTime;

public class Ex00_Article {
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
