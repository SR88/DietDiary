package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.FoodAndEntry;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
/**
 * Created By Seth Sneddon Feb 2019
 */
@Dao
public interface FoodAndEntryDAO {

    /**
     * get a list of foods that have an entry
     * @return compound class object
     */
    @Transaction
    @Query("Select * from food")
    LiveData<List<FoodAndEntry>> getFoodAndEntryList();

    /**
     * gets compound class object that has a particular food id
     * @param id
     * @return
     */
    @Transaction
    @Query("Select * from food where id = :id")
    List<FoodAndEntry> getFoodAndEntryById(int id);
}
