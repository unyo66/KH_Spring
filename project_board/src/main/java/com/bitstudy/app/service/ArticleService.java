package com.bitstudy.app.service;


import com.bitstudy.app.domain.type.SearchType;
import com.bitstudy.app.dto.ArticleDto;
import com.bitstudy.app.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor // 필수 필드에 대한 생성자를 자동으로 생성하는 롬복 에너테이션
@Transactional //
public class ArticleService {
    private final ArticleRepository articleRepository; // 아티클 관련 서비스 이기 때문에 ArticleRepository 필요

    // 검색용
    @org.springframework.transaction.annotation.Transactional(readOnly = true) // 트랜잭션을 읽기 전용 모드로 설정. 실수로 커밋해도 flush 가 되지 않아서 엔티티가 등록, 수정, 삭제 가 되지 않는다.
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        return Page.empty();
    }
}
