package com.shneddy.dietdiary.database;

import android.content.Context;

import com.shneddy.dietdiary.dao.DiaryEntryDAO;
import com.shneddy.dietdiary.dao.DiemDAO;
import com.shneddy.dietdiary.dao.FoodAndEntryDAO;
import com.shneddy.dietdiary.dao.FoodDAO;
import com.shneddy.dietdiary.dao.FoodTypeDAO;
import com.shneddy.dietdiary.dao.TypeAndFoodDAO;
import com.shneddy.dietdiary.entity.DiaryEntry;
import com.shneddy.dietdiary.entity.Diem;
import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.entity.FoodAndEntry;
import com.shneddy.dietdiary.entity.FoodType;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Food.class, FoodType.class, DiaryEntry.class, Diem.class}, version = 1)
public abstract class FoodDiaryDatabase extends RoomDatabase {

    private static FoodDiaryDatabase instance;

    public abstract FoodDAO foodDAO();
    public abstract DiaryEntryDAO entryDAO();
    public abstract FoodTypeDAO foodTypeDAO();
    public abstract TypeAndFoodDAO typeAndFoodDAO();
    public abstract FoodAndEntryDAO foodAndEntryDAO();
    public abstract DiemDAO diemDAO();

    public static synchronized FoodDiaryDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FoodDiaryDatabase.class, "fooddiary_database")
                    .fallbackToDestructiveMigration().allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
