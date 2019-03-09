package com.shneddy.dietdiary.entity;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/*
    Date Entity
    Diem is latin for date
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
