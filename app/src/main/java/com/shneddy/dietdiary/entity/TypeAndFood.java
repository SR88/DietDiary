package com.shneddy.dietdiary.entity;

import java.util.List;

import androidx.room.Embedded;
import androidx.room.Relation;
/**
 * Created By Seth Sneddon Feb 2019
 */
/**
 *  THIS CLASS IS UTILIZED IN JOIN OPERATION OF FOOD AND FOOD TYPE
 */
public class TypeAndFood {
    @Embedded
    public FoodType foodType;
    @Relation(parentColumn = "id", entityColumn = "foodTypeId")
    public List<Food> relFoodList;
}
