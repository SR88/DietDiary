package com.shneddy.dietdiary.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys =
                @ForeignKey(entity = FoodType.class,
                        parentColumns = "id",
                        childColumns = "foodTypeId"
                ))
public class Food {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private double gramsSugar;
    private int foodTypeId;

    public Food(String name, double gramsSugar, int foodTypeId) {
        this.name = name;
        this.gramsSugar = gramsSugar;
        this.foodTypeId = foodTypeId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGramsSugar() {
        return gramsSugar;
    }

    public void setGramsSugar(double gramsSugar) {
        this.gramsSugar = gramsSugar;
    }

    public void setFoodTypeId(int foodTypeId) {
        this.foodTypeId = foodTypeId;
    }

    public String getName() {
        return name;
    }

    public int getFoodTypeId() {
        return foodTypeId;
    }


    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gramsSugar=" + gramsSugar +
                ", foodTypeId=" + foodTypeId +
                '}';
    }
}
