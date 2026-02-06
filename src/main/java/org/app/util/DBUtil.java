package org.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static final Properties prop = new Properties();

    static {
        try (
                InputStream input = DBUtil.class.getClassLoader().getResourceAsStream("config.properties")
        ) {

            if (input == null) {
                throw new RuntimeException("Sorry, unable to find config.properties in the classpath");
            }

            prop.load(input);

            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                prop.getProperty("db.url"),
                prop.getProperty("db.user"),
                prop.getProperty("db.password")
        );
    }
}
