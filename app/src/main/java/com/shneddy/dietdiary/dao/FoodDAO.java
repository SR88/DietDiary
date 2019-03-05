package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.entity.FoodAndType;
import com.shneddy.dietdiary.entity.FoodAndTypeData;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.shneddy.dietdiary.entity.FoodType;
@Dao
public interface FoodDAO {

    FoodType foodtype = null;

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Food food);

    @Update
    void update(Food food);

    @Delete
    void delete(Food food);

    @Query("DELETE FROM food")
    void deleteAllFoods();

    @Query("SELECT * FROM food")
    LiveData<List<Food>> getAllFoods();

    @Query("SELECT * FROM food")
    List<Food> getAllFoodsList();

    @Query("SELECT FOOD.id, FOOD.name, FOOD.gramsSugar, FOOD.foodTypeId, FOOD_TYPE.id, " +
            "FOOD_TYPE.type FROM FOOD LEFT OUTER JOIN FOOD_TYPE ON FOOD.foodTypeId = FOOD_TYPE.id")
    LiveData<List<FoodAndType>> foodsAndTypesList();
}
