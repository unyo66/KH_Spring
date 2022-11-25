package com.bitstudy.app.dao;

import com.bitstudy.app.domain.Ex01_BoardDto;

import java.util.List;
import java.util.Map;

public interface Ex05_BoardDao {
    // 게시글 총 개수 구하기
    int count();

    // insert
    int insert(Ex01_BoardDto dto);

    // 특정 게시글 한개 가져오기
    Ex01_BoardDto select(Integer bno);

    // 게시글 전체 가져오기 (정렬: 최근 게시물이 위로)
    List<Ex01_BoardDto> selectAll();

    // 게시글 전체 가져오기(특정 번호부터 몇개)
    List<Ex01_BoardDto> selectPage(Map map);

    // update
    // 게시글 수정
    int update(Ex01_BoardDto dto);

    // 조회수 1씩 올리기
    int increaseViewCount(Integer bno);

    // 리뷰수 1씩 올리기
//    public int increaseCommentCount(Map map) {
//        return session.update(namespace+"increaseCommentCount", map);
//    }
    int increaseCommentCount(Integer bno);

    // delete
    int deleteAll();

    int delete(Map map);
}
