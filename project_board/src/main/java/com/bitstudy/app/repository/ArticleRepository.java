package com.bitstudy.app.repository;

import com.bitstudy.app.domain.Article;
import com.bitstudy.app.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * QueryDsl 의 QuerydslPredicateExecutor 와 QuerydslBinderCustomizer 를 이용해서 검색기능 만들기
 * */
@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle> {
    /** QuerydslPredicateExecutor : Article 안의 모든 필드에 대한 기본 검색기능 추가
     *  순서  1. 바인딩
     *       2. 검색용 필드 추가
     * */


    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        // 1. 바인딩 : 현재 QuerydslPredicateExecutor 때문에 Article 에 있는 모든 필드에 대한 검색이 열려있음.
        // 근데 우리가 원하는건 선택적 필드만 검색에 사용되게 하는 것
        // 그래서 bindings.excludeUnlistedProperties 사용.
        // excludeUnlistedProperties 는 리스팅을 하지 않은 프로퍼티는 검색에 포함할지 말지 결정할 수 있는 메서드.
        bindings.excludeUnlistedProperties(true);

        // 2. 검색용 필드 추가
        // including 을 이용해서 title, content, create_by, create_at, hashtag 검색 가능하게 만들기
        // including 사용법 : root.필드명
        bindings.including(root.title, root.content, root.create_at, root.create_by, root.hashtag);

        // 3. 정확한 검색 말고 or 검색도 가능하게 하기
        // bindings.bind(root.title).first(StringExpression::likeIgnoreCase); //like "%${문자열}"
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); //like "%${문자열}%"
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.create_at).first(DateTimeExpression::eq);
        bindings.bind(root.create_by).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);

        //다 됐으면 빌드하고 Hal 가서 체크하기
    }
}
