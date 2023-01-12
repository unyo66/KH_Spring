package com.bitstudy.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/** 회원 관리에 대한 부분. Auditing 까지 연결(상속)해서 사용해야함 */

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "email" , unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class UserAccount extends AuditingFields {

    @Id @Column(nullable = false, length = 50) private String userId;
    @Setter @Column(nullable = false) private String userPw;
    @Setter @Column(length = 100) private String email;
    @Setter @Column(length = 100) private String nickname;
    @Setter private String memo;

    ///////////////////////////////////////////////////////////


    protected UserAccount() {}

    private UserAccount(String userId, String userPw, String email, String nickname, String memo) {
        this.userId = userId;
        this.userPw = userPw;
        this.email = email;
        this.nickname = nickname;
        this.memo = memo;
    }

    public static UserAccount of(String userId, String userPw, String email, String nickname, String memo){
        return new UserAccount(userId, userPw, email, nickname, memo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}