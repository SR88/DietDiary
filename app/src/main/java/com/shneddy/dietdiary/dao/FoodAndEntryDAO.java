package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.FoodAndEntry;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface FoodAndEntryDAO {

    @Query("Select * from food")
    LiveData<List<FoodAndEntry>> getFoodAndEntryList();

    @Query("Select * from food where id = :id")
    List<FoodAndEntry> getFoodAndEntryById(int id);
}
