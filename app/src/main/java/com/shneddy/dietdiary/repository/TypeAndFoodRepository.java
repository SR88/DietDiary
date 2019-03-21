package com.shneddy.dietdiary.repository;

import android.app.Application;

import com.shneddy.dietdiary.dao.TypeAndFoodDAO;
import com.shneddy.dietdiary.database.FoodDiaryDatabase;
import com.shneddy.dietdiary.entity.TypeAndFood;

import java.util.List;

import androidx.lifecycle.LiveData;
/**
 * Created By Seth Sneddon Feb 2019
 */
public class TypeAndFoodRepository {

    TypeAndFoodDAO typeAndFoodDAO;
    private LiveData<List<TypeAndFood>> listLiveData;
    private List<TypeAndFood> typeAndFoodByIdList;

    public TypeAndFoodRepository(Application application) {
        FoodDiaryDatabase database = FoodDiaryDatabase.getInstance(application);
        typeAndFoodDAO = database.typeAndFoodDAO();
        listLiveData = typeAndFoodDAO.getTypeAndFoodsList();
    }

    public LiveData<List<TypeAndFood>> getTypeAndFoodLiveData(){
        return listLiveData;
    }

    public List<TypeAndFood> getTypeAndFoodById(int id) {
        return typeAndFoodByIdList = typeAndFoodDAO.getFoodAndTypeById(id);
    }

}
