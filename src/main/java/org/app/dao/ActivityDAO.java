package org.app.dao;

import org.app.custom.Exception.InvalidTypeException;
import org.app.model.Activity;
import org.app.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAO {

    public void addActivity(Activity activity) throws SQLException, InvalidTypeException {
        String sql = "INSERT INTO activities (user_id, type, name, calories) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
            PreparedStatement st = conn.prepareStatement(sql)
        ) {

            // check if the type of the activity is MEAL or EXERCISES
            InvalidTypeException.checkIfValidType(activity.getType());

            st.setInt(1, activity.getUserId());
            st.setString(2, activity.getType());
            st.setString(3, activity.getName());
            st.setDouble(4, activity.getCalories());

            st.executeUpdate();
        }
    }

    public List<Activity> getFilteredActivities(int userId, String type) throws SQLException {
        List<Activity> list = new ArrayList<>();
        String sql = "SELECT * FROM activities WHERE user_id = ? AND (type = ? OR ? IS NULL)";

        try (Connection conn = DBUtil.getConnection();
        PreparedStatement st = conn.prepareStatement(sql)
        ) {
            st.setInt(1, userId);
            st.setString(2, type);
            st.setString(3, type);

            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                Activity a = new Activity();

                a.setId(rs.getInt("id"));
                a.setUserId(rs.getInt("user_id"));
                a.setType(rs.getString("type"));
                a.setName(rs.getString("name"));
                a.setCalories(rs.getDouble("calories"));

                list.add(a);
            }
        }

        return list;
    }
}
