/*insert / delete는 어떻게 돌아가는가? */
package com.bitstudy.app;

import com.bitstudy.app.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/*
 *   Mysql DB에 연결해서 데이터를 저장, 읽어오기
 * 1) insertUser 메서드 @Test 모드 생성
 * 2) select, update, delete @Test 생성
 * */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}) //
public class Ex10_SpringJDBCTest3 {
    @Autowired
    DataSource ds;

    @Test
    public void insertUserTest() throws Exception {
        assertTrue(deleteUser("아이디1") == 1);

        User user = new User("아이디1", "1111", "이름1", "aaa@aaa.com", new Date(), "tw", new Date());
        int rowCount = insertUser(user);
        assertTrue(rowCount == 1);

    }
    @Test
    public void deleteUserTest() throws SQLException {
        assertTrue(deleteUser("아이디1") == 1);
    }

    @Test
    public void updateUserTest() throws SQLException {
        deleteUser("아이디1");
        User user = new User("아이디1", "1111", "이름1", "aaa@aaa.com", new Date(), "tw", new Date());
        int rowCount = insertUser(user);
        assertTrue(rowCount == 1);

        user = new User("아이디1", "1234", "홍길동", "aaa@aaa.com", new Date(), "tw", new Date());
        assertTrue(updateUser(user) == 1);
    }
    /*############ updateUser #############*/

    public int updateUser(User user) throws SQLException {
        Connection conn = ds.getConnection();
        String sql = "update user_info set pw=?, name=? where id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getPw());
        pstmt.setString(2, user.getName());
        pstmt.setString(3, user.getId());

        return pstmt.executeUpdate();
    }


    /*############ deleteUser #############*/
    public int deleteUser(String id) throws SQLException {
        Connection conn = ds.getConnection();
        String sql = "delete from user_info where id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate();
    }

    /*############ insertUser #############*/
    public int insertUser(User user) throws SQLException {
        Connection conn = ds.getConnection();
//        String sql = "insert into user_info values ('아이디1', '1111', '이름1', 'aaa@aaa.com', '1999-01-01', 'tw', now())";
//        String sql = "insert into user_info values ('" + user.getId() + "', '" + user.getPw() + "', '" + user.getName() + "', '" + user.getEmail() + "', '" + user.getBirth() + "', '" + user.getSns() + "', now())";
        String sql = "insert into user_info values (?, ?, ?, ?, now(), ?, now())";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getPw());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getEmail());
        //User클래스에서 birth는 util.Date 타입임. 그걸 sql.Date 타입으로 변환해줘야 함.
//        pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(5, user.getSns());
//        pstmt.executeUpdate(); //select 만 executeQuery, 나머지는 Update

        int rowCount = pstmt.executeUpdate();

        return rowCount;

    }
}