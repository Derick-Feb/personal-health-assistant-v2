package org.app.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String type; // MEAL OR EXERCISES
    private String name;
    private double calories;

    @Column(name = "created_at")
    private Timestamp createdAt;

    public Activity() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getCalories() { return calories; }
    public void setCalories(double calories) { this.calories = calories; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public Timestamp getCreatedAt() { return createdAt; }
}
