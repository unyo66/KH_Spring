package com.bitstudy.app.service;

import com.bitstudy.app.domain.Article;
import com.bitstudy.app.domain.UserAccount;
import com.bitstudy.app.domain.type.SearchType;
import com.bitstudy.app.dto.ArticleDto;
import com.bitstudy.app.dto.ArticleWithCommentsDto;
import com.bitstudy.app.dto.CommentDto;
import com.bitstudy.app.dto.UserAccountDto;
import com.bitstudy.app.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;


//import static org.junit.jupiter.api.Assertions.*;


/** 서비스 비지니스 로직은 슬라이스 테스트 기능 사용 안하고 만들어볼거임
    스프링부트 어플리케이션 컨셑그스가 뜨는데 걸리는 시간을 없애려고 한다.
    디펜던시가 추가되야 하는 부분에는 Mocking 을 하는 방식으로 한다.
    그래서 많이 사용하는 라이브러리가 mokito 라는게 있다. (스프링 테스트 패키지에 내장되어 있음.)

 @ExtendWith(MockitoExtension.class) 쓰면 된다.
 */

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    /* Mock을 주입하는 거에다가 @InjectMocks 을 달아줘야 한다. 그 외의 것들 한테는 @Mock 달아준다. */
    @InjectMocks
    private ArticleService sut; // sut - system under test. 테스트 짤때 사용하는 이름중 하나. 이건 테스트 대상이다 라는 뜻

    @Mock
    private ArticleRepository articleRepository; // 의존하는걸 가져와야 함. (테스트 중간에 mocking 할때 필요)

    /** 3. 페이지네이션 */

    /* 1. 검색 */
    @DisplayName("검색어 없이 게시글 검색하면, 게시글 리스트를 반환")
    @Test
    void searchNoKeywordReturnAll() {
        //Given - 페이지의 기능을 넣기
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findAll(pageable)).willReturn(Page.empty());

        //When - 입력없는지(null) 실제 테스트 돌리는 부분
        Page<ArticleDto> articles = sut.searchArticles(null, null, pageable);

        //Then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findAll(pageable);
    }

    @DisplayName("검색어 넣어서 게시글 검색하면, 게시글 리스트를 반환")
    @Test
    void searchWithKeywordReturnAll() {
        //Given - 페이지의 기능을 넣기
        SearchType searchType = SearchType.TITLE;
        String searchKeyword = "title";
        Pageable pageable = Pageable.ofSize(20);
        given(articleRepository.findByTitleContaining(searchKeyword, pageable)).willReturn(Page.empty());

        //When - 입력없는지(null) 실제 테스트 돌리는 부분
        Page<ArticleDto> articles = sut.searchArticles(searchType, searchKeyword, pageable);

        //Then
        assertThat(articles).isEmpty();
        then(articleRepository).should().findByTitleContaining(searchKeyword, pageable);
    }

    /** 2. 게시글 하나 호출*/
    @DisplayName("게시글 선택하면, 게시글 하나 반환")
    @Test
    void selectReturnOne() {
        //Given - Article 하나 만들기
        Article article = createTestArticle();
        Long articleId = 1L;
        /* Optional.of(article) : 결과가 article 이 나올수도 있고 아닐수도 있어 */
        given(articleRepository.findById(articleId)).willReturn(Optional.of(article));

        //When - 입력없는지(null) 실제 테스트 돌리는 부분
        ArticleWithCommentsDto articleWithCommentsDto = sut.getArticle(articleId);

        //Then
        assertThat(articleWithCommentsDto)
                .hasFieldOrPropertyWithValue("title", article.getTitle())
                .hasFieldOrPropertyWithValue("content", article.getContent())
                .hasFieldOrPropertyWithValue("hashtag", article.getHashtag());
        then(articleRepository).should().findById(articleId);
    }

    /** 3. 게시글 생성 */
    @DisplayName("게시글 생성")
    @Test
    void getInfoInsertArticle() {
        //Given - Article 하나 만들기
        ArticleDto articleDto = createArticleDto();
        /* any : 어떤 Article.class 가 들어오든 상관없다 */
        given(articleRepository.save(any(Article.class))).willReturn(createTestArticle());

        //When
        sut.saveArticle(articleDto);
        //Then
        then(articleRepository).should().save(any(Article.class));
    }

    /** 4. 게시글 수정 */
    @DisplayName("게시글 수정")
    @Test
    void getInfoUpdateArticle() {
        //Given
        ArticleDto articleDto = createArticleDto("title", "content", "#java");
        Article article = createTestArticle();
        /* articleDto 의 id 를 가져와서 (record 라 id()로 get) 해당 id 를 가진 데이터를 참조하게 하기 (getReferenceById) */
        given(articleRepository.getReferenceById(articleDto.id()))
                // 예외처리
                .willReturn(article);

        //When
        sut.updateArticle(articleDto);

        //Then
        assertThat(article)
                .hasFieldOrPropertyWithValue("title", articleDto.title())
                .hasFieldOrPropertyWithValue("content", articleDto.content())
                .hasFieldOrPropertyWithValue("hashtag", articleDto.hashtag());
        then(articleRepository).should().getReferenceById(articleDto.id());

    }

    @DisplayName("게시글 삭제")
    @Test
    void getInfoDeleteArticle() {
        //Given
        long expected = 0L;
        given(articleRepository.count()).willReturn(expected);
        //When
        long actual = sut.getArticleCount();
        //Then
        assertThat(actual).isEqualTo(expected);
        then(articleRepository).should().count();
    }


    @DisplayName("게시글 개수 구하기")
    @Test
    void returnArticleCount() {
        //Given

        //When

        //Then
    }


    private UserAccount createTestUserAccount() {
        return UserAccount.of(
                "bitstudy",
                "password",
                "bitstudy@email.com",
                "bitstudy",
                "memomemo"
        );
    }
    private Article createTestArticle() {
        return Article.of(
                createTestUserAccount(),
                "title",
                "content",
                "#java"
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(1L, "bitstudy", "asdf", "bitstudy@email.com", "bitstudy", "memomemo", LocalDateTime.now(), "bitstudy", LocalDateTime.now(), "bitstudy");
    }
    private ArticleDto createArticleDto() {
        return createArticleDto("title", "content", "#java");
    }

    private ArticleDto createArticleDto(String title, String content, String hashtag) {
        return ArticleDto.of(1L, createUserAccountDto(), title, content, hashtag, LocalDateTime.now(), "bitstudy", LocalDateTime.now(), "bitstudy");
    }
}















