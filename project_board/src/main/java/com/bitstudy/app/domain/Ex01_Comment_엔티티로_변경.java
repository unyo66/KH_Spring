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

@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "create_at"),
        @Index(columnList = "create_by")
})
@Entity
@Getter
@ToString
public class Ex01_Comment_엔티티로_변경 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*  연관관계 매핑
    *   연관관계 없이 만들면 private Long article_id; 이런식으로(관계형 DB 스타일)
    *   그런데 여기서는 Article 과 Comment 가 관계를 맺고 있는걸 객체지향적으로 표현하기 위해 이렇게 씀.
    * */
    @Setter
    @ManyToOne(optional = false)
    private Article article;


    @Setter
    @Column(nullable = false, length = 500)
    private String content;


    //메타데이터
    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime create_at;
    @Column(nullable = false, length = 100)
    @CreatedBy
    private String create_by;
    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime modify_at;
    @Column(nullable = false, length = 100)
    @LastModifiedBy
    private String modify_by;
}
