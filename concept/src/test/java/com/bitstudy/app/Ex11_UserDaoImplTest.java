package com.bitstudy.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-contextTest.xml"})
public class Ex11_UserDaoImplTest {
    @Autowired
    DataSource ds;

    @Autowired
    Ex11_UserDao userDao;

    @Test
    public void deleteAll() throws Exception {
        Connection conn = ds.getConnection();
        String sql = "delete from user_info";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.executeUpdate();
    }


    @Test
    public void insertUser() throws Exception {
        /*삽입하려는 ID가 있는지 확인하고 없으면 DB에 넣기*/
        String id = "asdf";

        if (userDao.selectUser(id) == null) {
            User user = new User("asdf", "1234", "홍길동", "aaa@aaa.com", new Date(), "twitter", new Date());
            userDao.insertUser(user);

            System.out.println("가입 성공");
        }
        else {
            System.out.println("이미 사용 중인 아이디입니다.");
        }
    }

    @Test
    public void deleteUser() throws Exception {
        String id = "asdf";

        if (userDao.selectUser(id) != null) {
            userDao.deleteUser(id);
            System.out.println("삭제 성공");
        }
        else {
            System.out.println("아이디가 없습니다.");
        }
    }

    @Test
    public void selectUser() throws Exception {
        String id = "asdf";
        System.out.println(userDao.selectUser(id));
    }

    @Test
    public void updateUser() throws Exception {
        /*Date 1번 방법*/
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(sdf.format(cal.getTime()));
        System.out.println("날짜2222 : " + date);

        /*Date 2번 방법*/
        LocalDate now = LocalDate.now();
        now.getYear();

        Calendar cal2 = Calendar.getInstance();
        cal2.clear();//Calendar에서 시분초를 00:00:00로 초기화
        cal2.set(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        System.out.println("날짜3333 : " + cal2);
    }
}
