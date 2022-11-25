package com.bitstudy.app;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;

/*
* MySql dbㄹㅡㄹ 인텔리j로 읽어오고 삭제하기
*
* 1. mvn repo -> mysql 검색
* 2. MySql Connector/j 최신버전 선택
* 3. pom파일 dependency에 넣고 업데이트
* */
public class Ex09_JDBC {
    public static void main(String[] args) {
        String DB_URL = "jdbc:mysql://localhost:3306/test"; // 연결할 DB : test
        String DB_ID = "bitstudy";
        String DB_PW = "1234";

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;


        try {
            /*Connection은 객체라고 하지만 사실은 인터페이스임
            * DriverManager의 getConnection 메서드를 이용해서 연결정보를 주기*/
            conn = DriverManager.getConnection(DB_URL, DB_ID, DB_PW);

            /* Statement(인터페이스) : 쿼리 담아서 보낼 객체 만들기 */
            stmt = conn.createStatement();

            /*할일 생성해서 변수에 담기*/
            String sql = "SELECT now()";

            /*쿼리 실행한 내용을 rs에 담기*/
            rs = stmt.executeQuery(sql);

            /*rs가 내용을 가지고 있으면 뽑아와*/
            while (rs.next()) {
                String currDate = rs.getString(1); // rs의 첫번째 줄 가져다가 currDate에 넣기
                System.out.println(currDate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
}
