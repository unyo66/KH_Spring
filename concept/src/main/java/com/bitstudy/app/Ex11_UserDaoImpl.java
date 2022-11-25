package com.bitstudy.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;

/** 이번 예제에서 할일
 *  UserDao 만들고 LoginController, RegisterController 만들어서 db 접근
 *  
 *  - 순서 - 
 *  1) Test4 가져와서 @Test 관련 코드 삭제
 * */

//@Component // Repository가 Component에 포함되어 있음. Component만 쓰면 장땡이지만 효율이 조금 떨어짐.
@Repository // 원래 이게 맞음
public class Ex11_UserDaoImpl implements Ex11_UserDao {
    @Autowired
    DataSource ds;

    @Autowired
    Ex11_UserDao userDao;

    @Override
    public void deleteAll() throws Exception {
        Connection conn = ds.getConnection();
        String sql = "delete from user_info";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
    }
    @Override
    public void close(AutoCloseable... autoClose) {
        for(AutoCloseable ac : autoClose) {
            if(ac!=null) {try {ac.close();} catch (Exception e) {    throw new RuntimeException(e);}}
        }
    }

    @Override
    public int insertUser(User user) throws Exception {
        Connection conn=null;
        PreparedStatement pstmt=null;
        try {
            conn = ds.getConnection();// 1) 데이터베이스 연결
            String sql="insert into user_info values (?,?,?,?,now(),?,now())";// 2) sql문 준비
            // 3) sql 실행
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getPw());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
//            pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));// getBirth() 의 타입 java.util.Date 를 java.sql.Date로 변경해줘야함
            pstmt.setString(5, user.getSns());

            return pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            close(pstmt, conn);
        }
    }

    @Override
    public User selectUser(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "select * from user_info where id=?";

            conn = ds.getConnection();// 1) 데이터베이스 연결

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery(); // select 일때는 executeQuery

            if(rs.next()) {
                User user = new User();
                user.setId(rs.getString(1));
                user.setPw(rs.getString(2));
                user.setName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setBirth(new Date( rs.getDate(5).getTime() ));
                user.setSns(rs.getString(6));
                user.setJoinDate(new Date(rs.getTimestamp(7).getTime()));
                return user;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs, pstmt, conn);
        }
    }

    @Override
    public int deleteUser(String id) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            String sql = "delete from user_info where id = ?";

            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt, conn);
        }
    }

    @Override
    public int updateUser(User user) throws Exception {

        String sql = "update user_info set pw=?, name=?, birth=?, joinDate=? where id = ?";
        try (
            Connection conn = ds.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ){
            pstmt.setString(1, user.getPw());
            pstmt.setString(2, user.getName());
            pstmt.setDate(3, new java.sql.Date(user.getBirth().getTime()));
            pstmt.setTimestamp(4, new Timestamp(user.getJoinDate().getTime()));
            pstmt.setString(5, user.getId());

            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void main() throws SQLException {
//
//        Connection conn = ds.getConnection();// DB 연결을 얻는 부분.
//        System.out.println("conn: " + conn);
//
//        assertTrue(conn!=null);
    }
}