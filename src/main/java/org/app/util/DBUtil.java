package org.app.util;

import java.sql.*;

public class DBUtil {
    private static final String URL = "jdbc:postgresql://localhost:5433/health_app";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "dede";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
