package com.shneddy.dietdiary.entity;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;

public class TypeAndFood {
    @Embedded
    public FoodType foodType;
    @Relation(parentColumn = "id", entityColumn = "foodTypeId")
    public List<Food> relFoodList;
}
