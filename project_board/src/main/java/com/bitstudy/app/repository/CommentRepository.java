package com.bitstudy.app.repository;

import com.bitstudy.app.domain.Article;
import com.bitstudy.app.domain.Comment;
import com.bitstudy.app.domain.QArticle;
import com.bitstudy.app.domain.QComment;
import com.bitstudy.app.dto.CommentDto;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CommentRepository extends
        JpaRepository<Comment, Long>,
        QuerydslPredicateExecutor<Comment>,
        QuerydslBinderCustomizer<QComment> {

    /** 검색 메서드 */
    List<Comment> findByArticleId(long articleId);

    @Override
    default void customize(QuerydslBindings bindings, QComment root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.content, root.createdAt, root.createdBy);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}
