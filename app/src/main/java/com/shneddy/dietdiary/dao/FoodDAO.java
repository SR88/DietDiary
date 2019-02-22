package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.Food;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface FoodDAO {


    @Insert
    void insert(Food food);

    @Update
    void update(Food food);

    @Delete
    void delete(Food food);

    @Query("DELETE FROM food")
    void deleteAllFoodTypes();

    @Query("SELECT * FROM food")
    LiveData<List<Food>> getAllFoods();


}
