package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.FoodDiary;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface FoodDiaryDAO {

    @Insert
    void insert(FoodDiary foodDiary);

    @Update
    void update(FoodDiary foodDiary);

    @Delete
    void delete(FoodDiary foodDiary);

    @Query("DELETE FROM fooddiary")
    void deleteAllFoodTypes();

    @Query("SELECT * FROM fooddiary")
    LiveData<List<FoodDiary>> getAllDiaryEntries();


}
