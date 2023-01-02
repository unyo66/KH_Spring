package com.bitstudy.app.repository;

import com.bitstudy.app.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource
public interface Ex07_2_CommentRepository extends JpaRepository<Comment, Long> {
}
