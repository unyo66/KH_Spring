package com.bitstudy.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/*  할일 : Lombok 사용하기
*
*   순서
*   1) Lombok을 이용해서 클래스를 엔티티로 변경
*   2) getter / setter, toString 등의 Lombok 에너테이션 사용
*   3) 동등성, 동일성 비교할 수 있는 코드 넣기
* */

/*  중복코드 관리
*   Article과 Comment 에 있는 공통 필드(메타데이터, ID제외)들을 별도로 빼서 관리할거임.
*   이유는 앞으로 Article과 Comment처럼 엮여있는 테이블 만들경우, 모든 domain 안의 파일에 많은 중복코드가 들어감.
*   그래서 별도 파일에 공통되는거 다 몰아넣고 사용해보기
*
*   추출법
*   1) @Embedded : 공통되는 필드들을 하나의 클래스로 만들고 @Embedded 있는 곳에서 치환하는 방식
*           Class Tmp{}
*           @Embedded
*           Tmp tmp;
*
*  *2) @MappedSuperClass : 에너테이션이 붙은곳에서 사용
*
* */

@EntityListeners(AuditingEntityListener.class) //얘없으면 테스트 중 createdAt 때문에 에러남(Ex04관련)

/*
* @Table : 엔티티와 매핑할 정보를 지정하고
*       사용법 : @Index(name="이름", columnList="사용할 컬럼명")
*       name 생략하면 원래 이름 사용
* @Index : 데이터베이스 인덱스는 추가, 쓰기, 저장 공간을 희생해서 데이터 검색 속도를 향상시키는 데이터 구조(@Entity와 세트)
* */
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
@Getter
@ToString(callSuper = true) // 모든 필드의 toString 생성
// 상위(UserAccount)에 있는 toString 까지 출력할 수 있도록 callSuper 넣음
public class Article extends AuditingFields {

    @Id //PK 지정 에너테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @ManyToOne(optional = false) // 단방향
    @JoinColumn(name="userId")
    private UserAccount userAccount;


    /* id와 메타데이터는 직접 set을 하면 안되니까 얘네만 따로 세터 주기*/
    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false, length = 10000)
    private String content;

    @Setter
    private String hashtag;



    /*  양방향 바인딩
    *
    *
    * */
    @OrderBy("id") // 양방향 바인딩을 할건데 정렬 기준을 id로 하겠다는 뜻
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude // 맨 위의 ToString이 얘까지 돌리면 순환참조로 인한 성능저하를 일으킬 수 있어서 제외 (보통 댓글이 글을 참조하기 때문에 Article에 걸어준다.
    private final Set<Comment> comments = new LinkedHashSet<>();



    /**
    * jpa auditing : jpa에서 자동으로 세팅하게 해줄때 사용하는 기능
    * 이거하려면 config 파일이 별도로 있어야 한다.
    * config 패키지 만들어 jpaConfig 클래스 만들자
    * */

    //메타데이터
//    @CreatedDate
//    @Column(nullable = false)
//    private LocalDateTime createdAt;
//
//    @CreatedBy
//    @Column(nullable = false, length = 100)
//    private String createdBy;
//
//    @LastModifiedDate
//    @Column(nullable = false)
//    private LocalDateTime modifiedAt;
//
//    @LastModifiedBy
//    @Column(nullable = false, length = 100)
//    private String modifiedBy;


    /** Entity를 만들때는 무조건 기본 생성자가 필요함.
     * public / protected만 가능한데, 아무데서도 기본생성자를 안쓰이게 하고 싶어서 protected로 변경함
     * **/

    protected Article(){}

    /** 사용자가 입력하는 값만 받기. 나머지는 시스템이 알아서 하게 해주면 됨.*/
    private Article(UserAccount userAccount, String title, String content, String hashtag) {
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(UserAccount userAccount, String title, String content, String hashtag){
        return new Article(userAccount, title, content, hashtag);
    }

    /*  정적 팩토리 메서드(팩토리 메서드 패턴 중 하나)
    *   객체 생성용 static method
    *   무조건 static으로 놔야함.
    *   장점 : static이라 생성자를 만들 필요없음 / return을 가지고 있기 때문에 상속 시 값을 확인할 수 있음
    *  */

    /*
    * 만약 Article 클래스를 이용해서 게시글들을 list에 담아서 화면을 구성할건데, 그걸 하려면 Collection을 이용해야 한다.
    * Collection : 객체의 모음(그룹)
    * Set : 중복 허용 안함, 순서도 보장하지 않음.
    * List : 중복 허용, 순서 있음
    * Map : Key, Value 구조로 되어 있는 특수 컬렉션
    *
    * list에 넣거나 또는 list에 있는 중복 요소를 제거하거나 정렬할때 비교를 할 수 있어야 하기 때문에
    * 동일성, 동등성 비교를 할 수 있는 equals와 hashcode를 구현해야 한다.
    *
    * 모든 데이터를 비교해도 되지만, 다 불러와서 비교하면 느려짐.
    * 사실 id만 같으면 두 엔티티가 같다는 뜻이니까 id만 가지고 비교하는걸 구현하자
    *
    * 체크박스 여러번 나올건데 id만 다 체크해서 만들면 됨
    * */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
