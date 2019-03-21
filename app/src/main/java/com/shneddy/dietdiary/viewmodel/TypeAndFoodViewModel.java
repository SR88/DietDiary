package com.shneddy.dietdiary.viewmodel;

import android.app.Application;

import com.shneddy.dietdiary.entity.TypeAndFood;
import com.shneddy.dietdiary.repository.TypeAndFoodRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
/**
 * Created By Seth Sneddon Feb 2019
 */
public class TypeAndFoodViewModel extends AndroidViewModel {

    private TypeAndFoodRepository typeAndFoodRepository;
    private LiveData<List<TypeAndFood>> mAllTypeAndFoodList;
    private List<TypeAndFood> mListTypesAndFoods;

    /**
     * Prepares the viewmodel for useage
     * @param application needed to perform the operations inside of this class
     */
    public TypeAndFoodViewModel(@NonNull Application application) {
        super(application);
        typeAndFoodRepository = new TypeAndFoodRepository(application);
        mAllTypeAndFoodList = typeAndFoodRepository.getTypeAndFoodLiveData();

    }

    /**
     * returns a livedata list of a JOIN between foodtypes and foods
     * @return livedata list of an inner join
     */
    public LiveData<List<TypeAndFood>> getTypeAndFoods(){
        return mAllTypeAndFoodList;
    }

    /**
     * Return JOIN set results of Food and FoodType by an id fk and pk
     * @param id used as a clause for the JOIN query
     * @return list of foodandtype
     */
    public List<TypeAndFood> getByIdList(int id){
        return mListTypesAndFoods = typeAndFoodRepository.getTypeAndFoodById(id);
    }
}
