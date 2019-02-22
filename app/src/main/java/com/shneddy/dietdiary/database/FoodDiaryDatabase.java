package com.shneddy.dietdiary.database;

import android.content.Context;

import com.shneddy.dietdiary.dao.FoodDAO;
import com.shneddy.dietdiary.dao.FoodDiaryDAO;
import com.shneddy.dietdiary.dao.FoodTypeDAO;
import com.shneddy.dietdiary.dao.PhaseDAO;
import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.entity.FoodDiary;
import com.shneddy.dietdiary.entity.FoodType;
import com.shneddy.dietdiary.entity.Phase;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Food.class, FoodType.class, Phase.class, FoodDiary.class}, version = 1)
public abstract class FoodDiaryDatabase extends RoomDatabase {

    private static FoodDiaryDatabase instance;

    public abstract FoodDAO foodDAO();
    public abstract FoodDiaryDAO foodDiaryDAO();
    public abstract FoodTypeDAO foodTypeDAO();
    public abstract PhaseDAO phaseDAO();

    public static synchronized FoodDiaryDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FoodDiaryDatabase.class, "fooddiary_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
