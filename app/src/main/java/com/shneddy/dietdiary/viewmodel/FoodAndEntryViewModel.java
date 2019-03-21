package com.shneddy.dietdiary.viewmodel;

import android.app.Application;

import com.shneddy.dietdiary.entity.FoodAndEntry;
import com.shneddy.dietdiary.repository.FoodAndEntryRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
/**
 * Created By Seth Sneddon Feb 2019
 */
public class FoodAndEntryViewModel extends AndroidViewModel {

    private FoodAndEntryRepository repository;
    private LiveData<List<FoodAndEntry>> mAllFoodAndEntryLiveData;
    private List<FoodAndEntry> mListFoodAndEntry;


    public FoodAndEntryViewModel(@NonNull Application application) {
        super(application);
        repository = new FoodAndEntryRepository(application);
        mAllFoodAndEntryLiveData = repository.getFoodAndEntryLiveData();
    }

    public LiveData<List<FoodAndEntry>> getFoodAndEntryLiveData(){
        return mAllFoodAndEntryLiveData;
    }

    public List<FoodAndEntry> getByIdList(int id){
        return mListFoodAndEntry = repository.getFoodAndEntryById(id);
    }
}
