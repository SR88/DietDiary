package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.entity.FoodAndType;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.shneddy.dietdiary.entity.FoodType;
/**
 * Created By Seth Sneddon Feb 2019
 */
@Dao
public interface FoodDAO {

    /**
     * inserts a food into the db
     * @param food
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Food food);

    /**
     * updates a food record in the db
     * @param food
     */
    @Update
    void update(Food food);

    /**
     * deletes a particular food in the db
     * @param food
     */
    @Delete
    void delete(Food food);

    /**
     * deletes all foods in the db
     */
    @Query("DELETE FROM food")
    void deleteAllFoods();

    /**
     * selects a particular food in the db based on id
     * @param id
     * @return returns a food
     */
    @Query("Select * from food where id = :id")
    Food getFoodById(int id);

    /**
     * gets foods with a name like a particular string
     * @param string used to search in the db query
     * @return list of matching results
     */
    @Query("select * from food where LOWER(name) like :string")
    List<Food> searchString(String string);

    /**
     * gets all foods from the db
     * @return live data list of foods
     */
    @Query("SELECT * FROM food")
    LiveData<List<Food>> getAllFoods();

    /**
     * selects all foods in db
     * @return list of all foods
     */
    @Query("SELECT * FROM food")
    List<Food> getAllFoodsList();

//    @Query("SELECT FOOD.id, FOOD.name, FOOD.gramsSugar, FOOD.foodTypeId, FOOD_TYPE.id, " +
//            "FOOD_TYPE.type FROM FOOD LEFT OUTER JOIN FOOD_TYPE ON FOOD.foodTypeId = FOOD_TYPE.id")
//    LiveData<List<FoodAndType>> foodsAndTypesList();

    /**
     * gets foods with a name like a particular string
     * @param name used to search
     * @return list of results
     */
    @Query("select * from food where lower(name) like (:name)")
    LiveData<List<Food>> search(String name);

    FoodType foodtype = null;
}
