package com.bitstudy.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

/*
 *   Test1은 수동방식임.
 *   Test2는 자동으로 주입받기
 *   장점 : Test가 여러개일때 ac를 new로 생성해 리소스를 많이 쓰게됨
 * */

@RunWith(SpringJUnit4ClassRunner.class) // Autowired 로 자동으로 받아와야하는데 폴더가 달라서 못받음. 지금 junit 으로 돌리고 있다는걸 알려줘야함.
                                        // Spring test 디펜던시에서 Spring TestContext Framework 받아와야 사용 가능.
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}) //
public class Ex10_SpringJDBCTest2 {
    @Autowired
    DataSource ds; // ApplicationContext 부분 직접 쓰지 않고 자동으로 주입받기

    @Test
    public void main() throws SQLException {
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // DB 연결을 얻는 부분
        System.out.println("conn : " + conn);

        assertTrue(conn!=null);
        //괄호 안의 조건식이 true면 테스트 성공(콘솔 위에 표시)
    }


}