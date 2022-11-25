package com.bitstudy.app.dao;

import com.bitstudy.app.domain.Ex01_BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*
* 1) BoardDao 다 만들고 인터페이스 만들기
* 2) mapper > boardMapper.xml 파일이랑 하나씩 매칭시키기
* 3) TDD
* */

@Repository
public class Ex05_BoardDaoImpl implements Ex05_BoardDao {
    @Autowired
    private SqlSession session;
    private static String namespace="com.bitstudy.app.dao.BoardMapper.";

    // 게시글 총 개수 구하기
    @Override
    public int count() {
        return session.selectOne(namespace+"count");
    }

    // insert
    @Override
    public int insert(Ex01_BoardDto dto) {
        return session.insert(namespace+"insert", dto);
    }


    // select

    // 특정 게시글 한개 가져오기
    @Override
    public Ex01_BoardDto select(Integer bno) {
        return session.selectOne(namespace+"select", bno);
    }

    // 게시글 전체 가져오기 (정렬: 최근 게시물이 위로)
    @Override
    public List<Ex01_BoardDto> selectAll() {
        return session.selectList(namespace+"selectAll");
    }

    // 게시글 전체 가져오기(특정 번호부터 몇개)
    @Override
    public List<Ex01_BoardDto> selectPage(Map map) {
        return session.selectList(namespace+"selectPage", map);
    }

    // update
    // 게시글 수정
    @Override
    public int update(Ex01_BoardDto dto) {
        return session.update(namespace+"update", dto);
    }
    // 조회수 1씩 올리기
    @Override
    public int increaseViewCount(Integer bno) {
        return session.update(namespace+"increaseViewCount", bno);
    }

    // 리뷰수 1씩 올리기
//    public int increaseCommentCount(Map map) {
//        return session.update(namespace+"increaseCommentCount", map);
//    }
    @Override
    public int increaseCommentCount(Integer bno) {
        return session.update(namespace+"increaseCommentCount", bno);
    }

    // delete
    @Override
    public int deleteAll() {
        return session.delete(namespace+"deleteAll");
    }
    @Override
    public int delete(Map map) {
        return session.delete(namespace+"delete", map);
    }
}