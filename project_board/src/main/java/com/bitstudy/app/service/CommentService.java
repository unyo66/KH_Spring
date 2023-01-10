package com.bitstudy.app.service;

import com.bitstudy.app.domain.Article;
import com.bitstudy.app.domain.Comment;
import com.bitstudy.app.dto.CommentDto;
import com.bitstudy.app.repository.ArticleRepository;
import com.bitstudy.app.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.EntityNotFoundException;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    /** 게시글 ID로 댓글 검색 */
    @Transactional(readOnly = true)
    public List<CommentDto> searchComment(long articleId) {
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(CommentDto::from)
                .toList();
    }

    /** 댓글 저장 */
    @Transactional(readOnly = true)
    public void saveComment(CommentDto commentDto) {
        try {
            commentRepository.save(commentDto.toEntity(articleRepository.getReferenceById(commentDto.articleId())));
        } catch (EntityNotFoundException e) {
            log.warn("댓글 저장 실패");
        }
    }

    /** 댓글 수정 */
    @Transactional(readOnly = true)
    public void updateComment(CommentDto commentDto) {
        try {
            Comment comment = commentRepository.getReferenceById(commentDto.id());
            if (commentDto.content() != null) {
                comment.setContent(commentDto.content());
            }
        } catch (EntityNotFoundException e) {
            log.warn("댓글 수정 실패");
        }
    }

    /** 댓글 삭제 */
    @Transactional(readOnly = true)
    public void deleteComment(Long commentId) {
        try {
            commentRepository.deleteById(commentId);
        } catch (EntityNotFoundException e) {

        }
    }
}
