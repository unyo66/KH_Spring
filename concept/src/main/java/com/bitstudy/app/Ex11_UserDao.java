package com.bitstudy.app;

import java.sql.SQLException;

public interface Ex11_UserDao {
    void deleteAll() throws Exception;

    void close(AutoCloseable... autoClose);

    int insertUser(User user) throws Exception;

    User selectUser(String id) throws SQLException;

    int deleteUser(String id) throws Exception;

    int updateUser(User user) throws Exception;
}
