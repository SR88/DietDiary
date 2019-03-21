package com.shneddy.dietdiary.entity;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;
/**
 * Created By Seth Sneddon Feb 2019
 */
public class DiemAndEntry {
    @Embedded
    public Diem diem;
    @Relation(parentColumn = "id", entityColumn = "diemId")
    public List<DiaryEntry> relDiaryList;
}
