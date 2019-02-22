package com.shneddy.dietdiary.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food_phase")
public class Phase {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String phase;

    public Phase(String phase) {
        this.phase = phase;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhase() {
        return phase;
    }
}
