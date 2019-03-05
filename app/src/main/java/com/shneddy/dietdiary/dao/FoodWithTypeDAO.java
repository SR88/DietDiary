package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.FoodAndType;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface FoodWithTypeDAO {

    @Query("Select * from food_type")
    public List<FoodAndType> getFoodAndType();
}
