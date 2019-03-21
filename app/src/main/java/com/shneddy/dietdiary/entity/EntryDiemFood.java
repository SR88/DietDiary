package com.shneddy.dietdiary.entity;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;
/**
 * Created By Seth Sneddon Feb 2019
 */

/**
 *  THIS CLASS IS UTILIZED TO JOIN DIEM, FOOD, AND DIARY ENTRY
 */
public class EntryDiemFood {
    @Embedded
    public Diem diem;
    @Relation(parentColumn = "diem.id", entityColumn = "diaryentry.diemId")
    public List<DiaryEntry> relDiaryList;
    @Relation(parentColumn = "food.id", entityColumn = "diaryentry.foodId")
    public List<Food> relFood;
}
