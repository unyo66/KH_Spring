package com.bitstudy.app.dto;

import com.bitstudy.app.domain.Article;
import com.bitstudy.app.domain.Comment;
import com.bitstudy.app.domain.UserAccount;

import java.time.LocalDateTime;

public record CommentDto(
        Long id,
        Long article_id,
        UserAccountDto userAccountDto,
        String content,
        LocalDateTime create_at,
        String create_by,
        LocalDateTime modify_at,
        String modify_by
) {

    public static CommentDto of(Long id, Long article_id, UserAccountDto userAccountDto, String content, LocalDateTime create_at, String create_by, LocalDateTime modify_at, String modify_by) {
        return new CommentDto(id, article_id, userAccountDto, content, create_at, create_by, modify_at, modify_by);
    }

    public static CommentDto from(Comment entity) {
        return new CommentDto(
                entity.getId(),
                entity.getArticle().getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getContent(),
                entity.getCreate_at(),
                entity.getCreate_by(),
                entity.getModify_at(),
                entity.getModify_by()
        );
    }

    public Comment toEntity(Article entity) {
        return Comment.of(
                entity,
                userAccountDto.toEntity(),
                content
        );
    }

}
