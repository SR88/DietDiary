package com.shneddy.dietdiary.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys =
                @ForeignKey(entity = FoodType.class,
                        parentColumns = "id",
                        childColumns = "foodTypeId"
                ), indices = @Index(value = "id", unique = true))
public class Food {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
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

    public Food getFood(){
        return this;
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
