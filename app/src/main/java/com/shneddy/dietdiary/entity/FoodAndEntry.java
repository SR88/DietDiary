package com.shneddy.dietdiary.entity;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;
/**
 * Created By Seth Sneddon Feb 2019
 */
/**
 *  THIS CLASS IS UTILIZED IN JOIN OPERATION OF FOOD AND DIARY ENTRY
 */
public class FoodAndEntry {
    @Embedded
    public Food food;
    @Relation(parentColumn = "id", entityColumn = "foodId")
    public List<DiaryEntry> relEntryList;
}
