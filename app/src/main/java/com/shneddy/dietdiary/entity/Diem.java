package com.shneddy.dietdiary.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
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
