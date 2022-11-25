package com.bitstudy.app;

public interface UserDao {
    void deleteAll() throws Exception;
    void close(AutoCloseable... autoClose);
    int insertUser(User user) throws Exception;
    int updateUser(User user) throws Exception;
    int deleteUser(User user) throws Exception;
    User selectUser(String id) throws Exception;
}
