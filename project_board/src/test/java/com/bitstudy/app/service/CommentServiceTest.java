package com.bitstudy.app.service;

import com.bitstudy.app.domain.Article;
import com.bitstudy.app.domain.Comment;
import com.bitstudy.app.domain.UserAccount;
import com.bitstudy.app.dto.CommentDto;
import com.bitstudy.app.dto.UserAccountDto;
import com.bitstudy.app.repository.ArticleRepository;
import com.bitstudy.app.repository.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {
    @InjectMocks
    private CommentService sut; //테스트 대상

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("댓글 검색")
    @Test
    void givenArticleIdReturnAll() {
        //Given
        Comment expected = createComment("content");
        Long articleId = 1L;
        given(commentRepository.findByArticleId(1L)).willReturn(List.of(expected));

        //When
        List<CommentDto> actual =  sut.searchComment(articleId);

        //Then
        assertThat(actual)
                .hasSize(1)
                        .first().hasFieldOrPropertyWithValue("content", expected.getContent());
        then(commentRepository).should().findByArticleId(articleId);
    }

    @DisplayName("댓글 저장")
    @Test
    void givenContentSaveComment() {
        //Given
        CommentDto commentDto = createCommentDto("댓글");
        given(articleRepository.getReferenceById(commentDto.articleId())).willReturn(createArticle());
//        given(commentRepository.save(any(CommentRepository.class))).willReturn()
        //When

        //Then
    }

    @DisplayName("댓글 수정")
    @Test
    void givenContentUpdateComment() {
        //Given 준비 파트
        String oldContent = "old content";
        String updateContent = "new content";
        Comment comment = createComment(oldContent);
        CommentDto commentDto = createCommentDto(updateContent);
        given(commentRepository.getReferenceById(commentDto.id())).willReturn(comment);

        //When 실행 파트
        sut.updateComment(commentDto);


        //Then 확인 파트
        assertThat(comment.getContent())
                .isNotEqualTo(oldContent)
                .isEqualTo(updateContent);

        /* 아티클 리포야 참조하니? */
        then(commentRepository).should().getReferenceById(commentDto.articleId());
    }

    @DisplayName("댓글 삭제")
    @Test
    void givenCommentIdDeleteComment() {
        //Given
        Long commentId = 1L;
        /* willReturn 값이 없을 경우 willDoNothing 을 써줌 */
        willDoNothing().given(commentRepository).deleteById(commentId);

        //When
        sut.deleteComment(commentId);

        //Then
        then(commentRepository).should().deleteById(commentId);
    }

    ///////////////////////////////////////////////////////
    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(1L, "bitstudy", "asdf", "bitstudy@email.com", "bitstudy", "memomemo", LocalDateTime.now(), "bitstudy", LocalDateTime.now(), "bitstudy");
    }
    private CommentDto createCommentDto(String content) {
        return CommentDto.of(
                1L,
                1L,
                createUserAccountDto(),
                content,
                LocalDateTime.now(),
                "bitstudy",
                LocalDateTime.now(),
                "bitstudy"
        );
    }
    private UserAccount createUserAccount() {
        return UserAccount.of(
                "bitstudy",
                "asdf",
                "bitstudy@email.com",
                "bitstudy",
                "memomemo"
        );
    }
    private Article createArticle() {
        return Article.of(
                createUserAccount(),
                "title",
                "content",
                "hashtag"
        );
    }
    private Comment createComment(String content) {
        return Comment.of(
                createArticle(),
                createUserAccount(),
                content
        );
    }
}
