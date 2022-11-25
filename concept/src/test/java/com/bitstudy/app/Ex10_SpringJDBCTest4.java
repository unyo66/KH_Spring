package com.bitstudy.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.assertTrue;

/*
* select는 어떻게 돌아가는가?
* */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}) //
public class Ex10_SpringJDBCTest4 {
    @Autowired
    DataSource ds;

    @Test
    public void selectUserTest() throws Exception {
        User user = selectUser("아이디1");

//        assertTrue(user.getId().equals("아이디1"));
        assertTrue(idpw(user, "1111"));
    }
    /*############ insertUser #############*/
    public boolean idpw(User user, String pw) {
        if (user.getPw().equals(pw))
            return true;
        return false;
    }
    public User selectUser(String id) throws SQLException {
        Connection conn = ds.getConnection();
        String sql = "select * from user_info where id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery(); //select 는 executeQuery

        while (rs.next()) {
            User user = new User();
            user.setId(rs.getString(1));
            user.setPw(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setBirth(new Date(rs.getDate(5).getTime()));
            user.setSns(rs.getString(6));
            user.setJoinDate(new Date(rs.getTimestamp(7).getTime()));
            return user;
        }
        return null;
    }

    @Test
    public void main() throws SQLException {

//        Connection conn = ds.getConnection();
//        System.out.println("conn : " + conn);

//        assertTrue(conn!=null);
        //괄호 안의 조건식이 true면 테스트 성공(콘솔 위에 표시)
    }


}