package com.bitstudy.app.dao;

import com.bitstudy.app.domain.Ex01_BoardDto;
import com.bitstudy.app.domain.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class Ex05_BoardDaoImplTest {

    @Autowired
    Ex05_BoardDao boardDao;

    @Test
    public void count() {
        int count = boardDao.count();
        System.out.println(count);
        assertTrue(count == 255);
    }

    @Test
    public void insert() {
    }

    @Test
    public void selectOne() {
    }

    @Test
    public void selectAll() {
    }

    @Test
    public void selectPage() {
    }

    @Test
    public void update() {
    }

    //조회수 1씩 증가시키기
    @Test
    public void increaseViewCount() {
    }
    //리뷰 수 1씩 증가시키기
    @Test
    public void increaseCommentCount() {
    }

    @Test
    public void deleteAll() {
        boardDao.deleteAll();
        assertTrue(boardDao.count() == 0);
    }

    @Test
    public void delete() {
    }
}