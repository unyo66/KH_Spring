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

/*  할일 : Lombok 사용하기
*
*   순서
*   1) Lombok을 이용해서 클래스를 엔티티로 변경
*   2) getter / setter, toString 등의 Lombok 어노테이션 사용
*   3) 동등성, 동일성 비교할 수 있는 코드 넣기
* */

/*
* @Table : 엔티티와 매핑할 정보를 지정하고
*       사용법 : @Index(name="이름", columnList="사용할 컬럼명")
*       name 생략하면 원래 이름 사용
* @Index : 데이터베이스 인덱스는 추가, 쓰기, 저장 공간을 희생해서 데이터 검색 속도를 향상시키는 데이터 구조(@Entity와 세트)
* */
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "create_at"),
        @Index(columnList = "create_by")
})
//@Entity
@Getter
@ToString
public class Ex01_Article_엔티티로_변경 {

    @Id //PK 지정 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* id와 메타데이터는 직접 set을 하면 안되니까 얘네만 따로 세터 주기*/
    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false, length = 10000)
    private String content;

    @Setter
    private String hashtag;



    /* 양방향 바인딩 */



    /*
    * jpa auditing : jpa에서 자동으로 세팅하게 해줄때 사용하는 기능
    * 이거하려면 config 파일이 별도로 있어야 한다.
    * config 패키지 만들어 jpaConfig 클래스 만들자
    * */

    //메타데이터
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime create_at;

    @CreatedBy
    @Column(nullable = false, length = 100)
    private String create_by;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modify_at;

    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String modify_by;


    /** Entity를 만들때는 무조건 기본 생성자가 필요함.
     * public / protected만 가능한데, 아무데서도 기본생성자를 안쓰이게 하고 싶어서 protected로 변경함
     * **/

    protected Ex01_Article_엔티티로_변경(){}

    /* 사용자가 입력하는 값만 받기. 나머지는 시스템이 알아서 하게 해주면 됨.*/
    private Ex01_Article_엔티티로_변경(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    /*  정적 팩토리 메서드(팩토리 메서드 패턴 중 하나)
    *   객체 생성용 static method
    *   무조건 static으로 놔야함.
    *   장점 : static이라 생성자를 만들 필요없음 / return을 가지고 있기 때문에 상속 시 값을 확인할 수 있음
    *  */
    public static Ex01_Article_엔티티로_변경 of (String title, String content, String hashtag) {
        return new Ex01_Article_엔티티로_변경(title, content, hashtag);
    }

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
        Ex01_Article_엔티티로_변경 article = (Ex01_Article_엔티티로_변경) o;
        return id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
