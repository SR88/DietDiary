package com.shneddy.dietdiary.entity;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class FoodAndEntry {
    @Embedded
    public Food food;
    @Relation(parentColumn = "id", entityColumn = "foodId")
    public List<DiaryEntry> relEntryList;
}
