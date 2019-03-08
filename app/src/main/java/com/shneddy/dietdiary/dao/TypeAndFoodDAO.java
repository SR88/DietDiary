package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.TypeAndFood;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface TypeAndFoodDAO {

    @Transaction
    @Query("Select * from food_type")
    LiveData<List<TypeAndFood>> getTypeAndFoodsList();

    @Query("Select * from food_type where food_type.id = :id")
    List<TypeAndFood> getFoodAndTypeById(int id);
}
