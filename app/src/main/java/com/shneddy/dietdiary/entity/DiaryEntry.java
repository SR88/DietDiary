package com.shneddy.dietdiary.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Food.class, parentColumns = "id", childColumns = "foodId", onDelete = CASCADE))
public class DiaryEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int foodId;
    private double portionSize;
    private String date;

    public DiaryEntry(int foodId, double portionSize, String date) {
        this.foodId = foodId;
        this.portionSize = portionSize;
        this.date = date;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getId() {
        return id;
    }

    public double getPortionSize() {
        return portionSize;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPortionSize(double portionSize) {
        this.portionSize = portionSize;
    }

    @Override
    public String toString() {
        return "DiaryEntry{" +
                "id=" + id +
                ", foodId=" + foodId +
                ", portionSize=" + portionSize +
                ", date='" + date + '\'' +
                '}';
    }
}
