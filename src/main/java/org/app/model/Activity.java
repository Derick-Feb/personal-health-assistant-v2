package org.app.model;

import java.sql.Timestamp;

public class Activity {

    private int id;
    private int userId;
    private String type; // MEAL OR EXERCISES
    private String name;
    private double calories;
    private Timestamp createdAt;

    public Activity() {}

    public int getId() { return id; }
    public void setId(int id) {}

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getCalories() { return calories; }
    public void setCalories(double calories) { this.calories = calories; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

}
