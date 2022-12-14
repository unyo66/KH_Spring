package com.wakeup.app.dao;

import com.wakeup.app.domain.WakeupDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WakeupDao {
    @Autowired
    SqlSession session;
    private static String namespace = "com.wakeup.app.dao.WakeupMapper.";

    public List<WakeupDto> selectMain() {
        return session.selectList(namespace+"selectMain");
    }
    public List<WakeupDto> selectSetting() {
        return session.selectList(namespace+"selectSetting");
    }

}