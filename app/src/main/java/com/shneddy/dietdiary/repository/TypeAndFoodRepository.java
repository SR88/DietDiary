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

    /**
     * Constructor for repository that performs the join operation and delivers the result sets
     * of the join between Food and FoodType
     * @param application required to perform the database operations
     */
    public TypeAndFoodRepository(Application application) {
        FoodDiaryDatabase database = FoodDiaryDatabase.getInstance(application);
        typeAndFoodDAO = database.typeAndFoodDAO();
        listLiveData = typeAndFoodDAO.getTypeAndFoodsList();
    }

    /**
     * Returns joined query results in livedata format for JOIN on Food and FoodType
     * @return results in live data format
     */
    public LiveData<List<TypeAndFood>> getTypeAndFoodLiveData(){
        return listLiveData;
    }

    /**
     * Returns joined results for Food and FoodType where id is equal to pkey
     * @param id pkey for join operation
     * @return list format of result set
     */
    public List<TypeAndFood> getTypeAndFoodById(int id) {
        return typeAndFoodByIdList = typeAndFoodDAO.getFoodAndTypeById(id);
    }

}
