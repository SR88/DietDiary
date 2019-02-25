package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.FoodType;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface FoodTypeDAO {

    @Insert
    void insert(FoodType foodType);

    @Update
    void update(FoodType foodType);

    @Delete
    void delete(FoodType foodType);

    @Query("DELETE FROM food_type")
    void deleteAllFoodTypes();

    @Query("SELECT * FROM food_type")
    LiveData<List<FoodType>> getAllFoodTypes();

    @Query("SELECT * FROM food_type")
    List<FoodType> getAllFoodTypesList();

}
