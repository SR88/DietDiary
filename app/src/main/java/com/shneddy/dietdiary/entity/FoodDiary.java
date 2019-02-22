package com.shneddy.dietdiary.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Food.class, parentColumns = "id", childColumns = "foodId"))
public class FoodDiary {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int foodId;
    private float portionSize;

    public FoodDiary(int foodId, float portionSize) {
        this.foodId = foodId;
        this.portionSize = portionSize;
    }

    public int getFoodId() {
        return foodId;
    }

    public float getPortionSize() {
        return portionSize;
    }

    public void setId(int id) {
        this.id = id;
    }
}
