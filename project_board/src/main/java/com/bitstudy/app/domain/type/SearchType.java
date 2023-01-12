package com.bitstudy.app.domain.type;

import lombok.Getter;

public enum SearchType {
    //    제목, 본문, id, 글쓴이, 해시태그
    TITLE("제목"), CONTENT("내용"), ID("아이디"), NICKNAME("작성자"), HASHTAG("해시태그");

    @Getter
    private final String description;

    SearchType(String description) {
        this.description = description;
    }
}