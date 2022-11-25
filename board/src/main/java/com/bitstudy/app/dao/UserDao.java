package com.bitstudy.app.dao;

import com.bitstudy.app.domain.UserDto;

import java.sql.SQLException;

public interface UserDao {
    void deleteAll() throws Exception;

    void close(AutoCloseable... autoClose);

    int insertUser(UserDto user) throws Exception;

    UserDto selectUser(String id) throws SQLException;

    int deleteUser(String id) throws Exception;

    int updateUser(UserDto user) throws Exception;
}
