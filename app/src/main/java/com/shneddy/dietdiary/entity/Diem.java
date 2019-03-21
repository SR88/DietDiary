package com.shneddy.dietdiary.entity;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created By Seth Sneddon Feb 2019
 */
/**
 *  OBJECT RELATIONAL MAPPING ENTITY OF DIEM
 *
 *  THIS CLASS CONTAINS GETTERS AND SETTERS
 *
 *  THIS CLASS EXHIBITS DATA ENCAPSULATIONS/HIDING
 */
@Entity
public class Diem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String date;

    @Ignore
    Date diemDate;

    public Date getDiemDate() {
        return diemDate;
    }

    public void setDiemDate(Date diemDate) {
        this.diemDate = diemDate;
    }

    public Diem(@NonNull String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Diem{" +
                "id=" + id +
                ", date='" + date + '\'' +
                '}';
    }
}
