package com.bitstudy.app.dto;

import com.bitstudy.app.domain.Article;
import com.bitstudy.app.domain.UserAccount;

import java.time.LocalDateTime;

public record UserAccountDto(
        Long id,
        String user_id,
        String user_pw,
        String email,
        String nickname,
        String memo,
        LocalDateTime create_at,
        String create_by,
        LocalDateTime modify_at,
        String modify_by
) {

    public static UserAccountDto of(Long id, String user_id, String user_pw, String email, String nickname, String memo, LocalDateTime create_at, String create_by, LocalDateTime modify_at, String modify_by) {
        return new UserAccountDto( id, user_id, user_pw, email, nickname, memo, create_at, create_by, modify_at, modify_by);
    }

    /////////////////////////////////////////////////////////////////////////////

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getId(),
                entity.getUser_id(),
                entity.getUser_pw(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getMemo(),
                entity.getCreate_at(),
                entity.getCreate_by(),
                entity.getModify_at(),
                entity.getModify_by()
        );
    }

    /* 위에거랑 반대. dto 를 주면 엔티티를 생성하는 메서드 */
    public UserAccount toEntity() {
        return UserAccount.of(
                user_id,
                user_pw,
                email,
                nickname,
                memo
        );
    }




}








