package com.shneddy.dietdiary.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food_type")
public class FoodType {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String type;
    private String description;

    public FoodType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
