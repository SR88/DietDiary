package com.shneddy.dietdiary.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = {
                @ForeignKey(entity = FoodType.class,
                        parentColumns = "id",
                        childColumns = "foodTypeId"
                ),
                @ForeignKey(entity = Phase.class,
                        parentColumns = "id",
                        childColumns = "foodPhaseId"
                )})
public class Food {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int foodTypeId;
    private int foodPhaseId;

    public Food(String name, int foodTypeId, int foodPhaseId) {
        this.name = name;
        this.foodTypeId = foodTypeId;
        this.foodPhaseId = foodPhaseId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getFoodTypeId() {
        return foodTypeId;
    }

    public int getFoodPhaseId() {
        return foodPhaseId;
    }
}
