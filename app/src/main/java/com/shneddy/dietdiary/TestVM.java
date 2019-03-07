package com.shneddy.dietdiary;

import android.app.Application;

import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.entity.FoodAndType;
import com.shneddy.dietdiary.repository.FoodRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class TestVM extends AndroidViewModel {

    private FoodRepository mFoodRepository;
    private LiveData<List<FoodAndType>> mAllFoodAndTypes;


    public TestVM(@NonNull Application application) {
        super(application);

        mFoodRepository = new FoodRepository(application);
        mAllFoodAndTypes = mFoodRepository.getAllFoodsAndTypes();
//
//        mAllFoodAndTypes = Transformations.switchMap(mFoodRepository.getAllFoodsAndTypes(),
//                v -> mFoodRepository.getAllFoodsAndTypes());

//        mAllFoodAndTypes = Transformations.switchMap(mFoodRepository.getAllFoodsAndTypes(),
//                v -> {
//                    if (mAllFoodAndTypes == null) {
//                        return mFoodRepository.getAllFoodsAndTypes();
//                    } else {
//                        return mAllFoodAndTypes;
//                    }
//                });

//        this.mAllFoodAndTypes = Transformations.switchMap(mFoodRepository.getAllFoods(), v -> mFoodRepository.getAllFoodsAndTypes());

    }

    public LiveData<List<FoodAndType>> getFoodAndTypes(){
        return mAllFoodAndTypes;
    }



    /*
        Food Methods
     */
    public void insertFood(Food food){
        mFoodRepository.insert(food);
    }

    public void updateFood(Food food){
        mFoodRepository.update(food);
    }

    public void deleteFood(Food food){
        mFoodRepository.delete(food);
    }

    public void deleteAllFood(){
        mFoodRepository.deleteAll();
    }


}
