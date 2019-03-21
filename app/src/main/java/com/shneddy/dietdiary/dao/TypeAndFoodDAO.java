package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.TypeAndFood;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;
/**
 * Created By Seth Sneddon Feb 2019
 */
@Dao
public interface TypeAndFoodDAO {

    /**
     * used to retrieve joined records from db
     * particularly food and type records
     * @return list of foods and types that go together
     */
    @Transaction
    @Query("Select * from food_type")
    LiveData<List<TypeAndFood>> getTypeAndFoodsList();

    /**
     * returns list of food and types based on a food type id
     * @param id of the food type
     * @return list of foods and types
     */
    @Transaction
    @Query("Select * from food_type where food_type.id = :id")
    List<TypeAndFood> getFoodAndTypeById(int id);
}
