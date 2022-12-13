package com.bitstudy.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAO {
    String url = "jdbc:mysql://localhost/filmap?serverTimezone=Asia/Seoul";
    String id = "root";
    String pw = "1234";
    Connection conn = null;
    PreparedStatement pstmt = null;

    public static void main(String[] args) {
        DAO DB = new DAO();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("DB연결 성공");

            PreviewDAO dao = new PreviewDAO();

            try {
                DB.conn = DriverManager.getConnection(DB.url, DB.id, DB.pw);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}