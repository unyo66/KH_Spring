package com.bitstudy.app;

import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/*
*   1. 테스트용 메서드에는 @Test 붙여야 함
*   2. 해당 메서드는 public void 이고 매개변수가 없어야 함
*   3. 메서드 끝에는 assertTrue 사용해야 함
* */
public class Ex10_SpringJDBCTest {
    @Test
    public void main() throws SQLException {
        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/root-context.xml");
        DataSource ds = ac.getBean(DataSource.class);
        Connection conn = ds.getConnection(); // DB 연결을 얻는 부분
        System.out.println("conn : " + conn);

        assertTrue(conn!=null);
        //괄호 안의 조건식이 true면 테스트 성공(콘솔 위에 표시)
    }


}