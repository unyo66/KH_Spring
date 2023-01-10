package com.bitstudy.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
@Getter
@ToString(callSuper = true)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*  연관관계 매핑
    *   연관관계 없이 만들면 private Long articleId; 이런식으로(관계형 DB 스타일)
    *   그런데 여기서는 Article 과 Comment 가 관계를 맺고 있는걸 객체지향적으로 표현하기 위해 이렇게 씀.
    * */ 
    @Setter
    @ManyToOne(optional = false)
    private Article article;

    @Setter
    @ManyToOne(optional = false)
    private UserAccount userAccount;


    @Setter
    @Column(nullable = false, length = 500)
    private String content;


    //메타데이터
    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;
    @Column(nullable = false, length = 100)
    @CreatedBy
    private String createdBy;
    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @Column(nullable = false, length = 100)
    @LastModifiedBy
    private String modifiedBy;

    protected Comment() {
    }

    private Comment(Article article, UserAccount userAccount, String content) {
        this.article = article;
        this.userAccount = userAccount;
        this.content = content;
    }

    public static Comment of(Article article, UserAccount userAccount, String content) {
        return new Comment(article, userAccount, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id.equals(comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}