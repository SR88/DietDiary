package com.shneddy.dietdiary.entity;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class EntryDiemFood {
    @Embedded
    public Diem diem;
    @Relation(parentColumn = "diem.id", entityColumn = "diaryentry.diemId")
    public List<DiaryEntry> relDiaryList;
    @Relation(parentColumn = "food.id", entityColumn = "diaryentry.foodId")
    public List<Food> relFood;
}
