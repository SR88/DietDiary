package com.shneddy.dietdiary.entity;

import java.util.List;
import java.util.Set;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Relation;
/**
 * Created By Seth Sneddon Feb 2019
 */
/**
 *  THIS CLASS IS UTILIZED IN JOIN OPERATION OF FOOD AND FOOD TYPE
 */
public class FoodAndType {
    @Embedded
    public FoodType foodType;
    @Relation(parentColumn = "id", entityColumn = "foodTypeId")
    public List<Food> food;

}
