package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.FoodType;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
/**
 * Created By Seth Sneddon Feb 2019
 */
@Dao
public interface FoodTypeDAO {

    /**
     * inserts a foodtype into the db
     * @param foodType
     */
    @Insert
    void insert(FoodType foodType);

    /**
     * updates a particular foodtype record in the db
     * @param foodType
     */
    @Update
    void update(FoodType foodType);

    /**
     * deletes a particular foodtype record in the db
     * @param foodType
     */
    @Delete
    void delete(FoodType foodType);

    /**
     * deletes all food types in the db
     */
    @Query("DELETE FROM food_type")
    void deleteAllFoodTypes();

    /**
     * gets all foodtype records in the db
     * @return live list of food types
     */
    @Query("SELECT * FROM food_type")
    LiveData<List<FoodType>> getAllFoodTypes();

    /**
     * gets all foodtype records in the db
     * @return list form of records
     */
    @Query("SELECT * FROM food_type")
    List<FoodType> getAllFoodTypesList();

}
