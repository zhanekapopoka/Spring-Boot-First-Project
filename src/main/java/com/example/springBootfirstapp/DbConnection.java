package com.example.springBootfirstapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/recipes_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Zhanel1234"; // ← твой пароль

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}