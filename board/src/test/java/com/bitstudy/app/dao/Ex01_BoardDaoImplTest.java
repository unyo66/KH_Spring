package com.bitstudy.app.dao;

import com.bitstudy.app.domain.Ex01_BoardDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

 /*
 * Dto를 이용해서 Dao가 제대로 동작하는지 확인하는 예제
 * 1) session.selectOne이 값을 하나만 제대로 가져오는지 확인
 */
 @RunWith(SpringJUnit4ClassRunner.class)
 @ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class Ex01_BoardDaoImplTest {
    @Autowired
    Ex01_BoardDao boardDao;

    // bno라는 숫자 보내서 글 정보 모두 가져오기
    @Test
    public void select() throws Exception{
        assertTrue(boardDao != null);
        System.out.println("boardDao : " + boardDao);
        Ex01_BoardDto boardDto = boardDao.select(1);
        System.out.println("boardDto : " + boardDto);
        assertTrue(boardDto.getBno() == 1);
    }

}