package com.bitstudy.app.dao;

import com.bitstudy.app.domain.Ex01_BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/*
* 1) BoardDao 만들고
* 2) interface화 하기
* 3) TDD 만들어서 mapper와 같이 동작하는지 보고 제대로 BoardDao 만들기
* */
@Repository
public class Ex01_BoardDaoImpl implements Ex01_BoardDao {
    @Autowired
    SqlSession session;
    private String namespace = "com.bitstudy.app.dao.BoardMapper.";

    @Override
    public Ex01_BoardDto select(int bno) {
        return session.selectOne(namespace + "select", bno);
    }
}
