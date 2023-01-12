package com.bitstudy.app.service;


import com.bitstudy.app.domain.Article;
import com.bitstudy.app.domain.type.SearchType;
import com.bitstudy.app.dto.ArticleDto;
import com.bitstudy.app.dto.ArticleWithCommentsDto;
import com.bitstudy.app.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Slf4j
@Service
@RequiredArgsConstructor // 필수 필드에 대한 생성자를 자동으로 생성하는 롬복 에너테이션
@Transactional //
public class ArticleService {
    private final ArticleRepository articleRepository; // 아티클 관련 서비스 이기 때문에 ArticleRepository 필요

    // 검색용
    @Transactional(readOnly = true) // 트랜잭션을 읽기 전용 모드로 설정. 실수로 커밋해도 flush 가 되지 않아서 엔티티가 등록, 수정, 삭제 가 되지 않는다.
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
//        return Page.empty();

        /**
         *  ArticleServiceTest 의 withoutKeywordReturnArticlesAll() 테스트 관련 실제 구현
         *  When 부분에서 searchArticles 메서드를 이용해서 값들을 전달함.
         *  사실 검색 관련 부분이기 때문에 검색어인 searchKeyword 를 이용해서 코드를 짤 거임.
         * */

        /*  검색어 없는 경우   */
        if (searchKeyword == null || searchKeyword.isBlank()) {
            /* 특정 페이지의 모든 article을 가져와라 (반환값은 Page) */
            /* map(ArticleDto::from) -> Page 안의 내용물을 ArticleDto 로 만들어 Page에 다시 넣어줌 (맞나????????) */
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        /*  검색어 있는 경우 (각 searchType 마다)  */

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtagContaining(searchKeyword, pageable).map(ArticleDto::from);
        };
    }

    /** 게시글 하나 호출 */
    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticle(Long articleId) {
        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다.")); //Dto로 변경
    }

    /** 게시글 생성 */
    public void saveArticle(ArticleDto articleDto) {
        articleRepository.save(articleDto.toEntity()); //Dto 를 엔티티로 바꾸기
    }

    /** 게시글 수정 */
    public void updateArticle(ArticleDto articleDto) {
        try {
            Article article = articleRepository.getReferenceById(articleDto.id());
            if (articleDto.title() != null) {
                article.setTitle(articleDto.title());
            }
            if (articleDto.content() != null) {
                article.setContent(articleDto.content());
            }
            article.setHashtag(articleDto.hashtag());
        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패. 게시글을 찾을 수 없습니다. dto : " + articleDto);
        }
    }

    /** 게시글 삭제 */
    public void deleteArticle(long articleId) {
        articleRepository.deleteById(articleId);
    }

    /** 게시글 개수 구하기 : 이전, 다음 버튼 비활성화용 */
    public long getArticleCount() {
        return articleRepository.count();
    }
}
