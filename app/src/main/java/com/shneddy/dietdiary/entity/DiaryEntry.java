package com.shneddy.dietdiary.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Created By Seth Sneddon Feb 2019
 */

/**
 *  OBJECT RELATIONAL MAPPING ENTITY OF DIARY ENTRY
 *
 *  THIS CLASS CONTAINS GETTERS AND SETTERS
 *
 *  THIS CLASS EXHIBITS DATA ENCAPSULATIONS/HIDING
 */
@Entity(foreignKeys = {@ForeignKey(entity = Food.class, parentColumns = "id", childColumns = "foodId", onDelete = CASCADE),
        @ForeignKey(entity = Diem.class, parentColumns = "id", childColumns = "diemId", onDelete = CASCADE)},
        indices = {@Index("foodId"), @Index("diemId")})
public class DiaryEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int foodId;
    private double portionSize;
    private int diemId;

    public DiaryEntry(int foodId, double portionSize, int diemId) {
        this.foodId = foodId;
        this.portionSize = portionSize;
        this.diemId = diemId;
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

    public int getDiemId() {
        return diemId;
    }

    public void setDiemId(int diemId) {
        this.diemId = diemId;
    }

    public void setPortionSize(double portionSize) {
        this.portionSize = portionSize;
    }


}
