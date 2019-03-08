package com.shneddy.dietdiary.repository;

import android.app.Application;

import com.shneddy.dietdiary.dao.FoodAndEntryDAO;
import com.shneddy.dietdiary.database.FoodDiaryDatabase;
import com.shneddy.dietdiary.entity.FoodAndEntry;

import java.util.List;

import androidx.lifecycle.LiveData;

public class FoodAndEntryRepository {

    FoodAndEntryDAO dao;
    private LiveData<List<FoodAndEntry>> listLiveData;
    private List<FoodAndEntry> foodAndEntryList;

    public FoodAndEntryRepository(Application application) {
        FoodDiaryDatabase db = FoodDiaryDatabase.getInstance(application);
        dao = db.foodAndEntryDAO();
        listLiveData = dao.getFoodAndEntryList();
    }

    public LiveData<List<FoodAndEntry>> getFoodAndEntryLiveData(){
        return listLiveData;
    }

    public List<FoodAndEntry> getFoodAndEntryById(int id){
        return foodAndEntryList = dao.getFoodAndEntryById(id);
    }
}
