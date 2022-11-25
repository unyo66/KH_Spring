package com.bitstudy.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/*
* Spring JDBC는 순수 JDBC 사용할때 불편함을 보완
*
* 방법 2가지
* 1. DriverManager (Ex09) : 코드에 정보 고정
* 2. AppContext : 외부저장소에서 정보 가져오기
* */
public class Ex10_SpringJDBC {
    public static void main(String[] args) throws Exception {

        /*##################### 1) DriverManager ########################
        * 이 방법으로 하면 직접 객체를 생성.
        * 그런데 DB 관련 정보가 바뀔때마다 코드를 다 수정하고 테스트해야함 -> 그냥 JDBC랑 별반 다를게 없음
        * */
//        String DB_Driver = "com.mysql.jdbc.Driver";
//        String DB_URL = "jdbc:mysql://localhost:3306/test";
//        String DB_ID = "bitstudy";
//        String DB_PW = "1234";
//
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName(DB_Driver);
//        ds.setUrl(DB_URL);
//        ds.setUsername(DB_ID);
//        ds.setPassword(DB_PW);
//
//        Connection conn = ds.getConnection(); // DB 연결을 얻는 부분
//        System.out.println("conn : " + conn);




        /*##################### 2) ApplicationContext ########################
        * 설정정보를 참고 IoC를 적용해서 빈의 생성, 관계설정 같은 제어작업을 총괄하게 함.
        * xml / txt같은 외부파일에 bean으로 등록해놓고 가져다쓸 수 있음.
        * */

        /* 외부파일에 정보 넣어두고 getBean으로 연결 */
        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/root-context.xml");
        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // DB 연결을 얻는 부분
        System.out.println("conn : " + conn);

        /*##################### 테스트 자동화 - TDD (Test Driven Development) ########################
        실제 코드로 사용하지 않고  TEST 폴더에 복사해서 테스트함.
        1. 빈공간 / 클래스명 우클릭 Goto Test
        2. junit4로 apply
         */


    }
}
