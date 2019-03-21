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

    public TypeAndFoodViewModel(@NonNull Application application) {
        super(application);
        typeAndFoodRepository = new TypeAndFoodRepository(application);
        mAllTypeAndFoodList = typeAndFoodRepository.getTypeAndFoodLiveData();

    }

    public LiveData<List<TypeAndFood>> getTypeAndFoods(){
        return mAllTypeAndFoodList;
    }

    public List<TypeAndFood> getByIdList(int id){
        return mListTypesAndFoods = typeAndFoodRepository.getTypeAndFoodById(id);
    }
}
