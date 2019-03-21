package com.shneddy.dietdiary.entity;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;
/**
 * Created By Seth Sneddon Feb 2019
 */
/**
 *  THIS CLASS IS UTILIZED IN JOIN OPERATION OF DIEM AND DIARY ENTRY
 */
public class DiemAndEntry {
    @Embedded
    public Diem diem;
    @Relation(parentColumn = "id", entityColumn = "diemId")
    public List<DiaryEntry> relDiaryList;
}
