package com.shneddy.dietdiary;

import android.app.Application;

import com.shneddy.dietdiary.entity.TypeAndFood;
import com.shneddy.dietdiary.repository.TypeAndFoodRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TestJoinVM extends AndroidViewModel {

    private TypeAndFoodRepository typeAndFoodRepository;
    private LiveData<List<TypeAndFood>> mAllTypeAndFoodList;


    public TestJoinVM(@NonNull Application application) {
        super(application);
        typeAndFoodRepository = new TypeAndFoodRepository(application);
        mAllTypeAndFoodList = typeAndFoodRepository.getTypeAndFoodLiveData();
    }

    public LiveData<List<TypeAndFood>> getTypeAndFoods(){
        return mAllTypeAndFoodList;
    }
}
