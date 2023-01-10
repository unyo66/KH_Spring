package com.bitstudy.app.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*  할일 : Article, Comment 의 중복필드 합치기
    1) Article 의 메타데이터들(auditing 관련 필드) 가져오기
    2) 클래스에 @MappedSuperclass 달아주기
    3) auditing 관련된 것들 다 가져오기
    4)
*
* */

@EntityListeners(AuditingEntityListener.class) //얘없으면 테스트 중 createdAt 때문에 에러남(Ex04관련)
@MappedSuperclass
@Getter
@ToString
public class AuditingFields {
    //메타데이터
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(nullable = false, length = 100)
    private String createdBy;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;

    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String modifiedBy;
}
