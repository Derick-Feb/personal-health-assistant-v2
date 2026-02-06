package org.app.dao;

import org.app.model.User;
import org.app.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public boolean register(User user) throws SQLException {
        String query = "INSERT INTO users(username, password) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement st = conn.prepareStatement(query);
        ) {
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.execute();

            int rows = st.executeUpdate();
            return rows > 0;
        }
    }

    public User validateUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DBUtil.getConnection();
            PreparedStatement st = conn.prepareStatement(query);
        ) {
            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                return user;
            }
        }

        return null;
    }
}
