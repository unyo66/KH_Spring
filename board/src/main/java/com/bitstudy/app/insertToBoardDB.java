package com.bitstudy.app;

import com.bitstudy.app.dao.UserDao;
import com.bitstudy.app.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;

public class insertToBoardDB {
    @Autowired
    UserDao userDao;

    public static void main(String[] args) {
        UserDto user = new UserDto();
    }
}
