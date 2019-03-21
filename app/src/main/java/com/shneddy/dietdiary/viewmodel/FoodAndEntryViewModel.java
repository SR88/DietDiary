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

    /**
     * ViewModel for joined query results
     * @param application required to perform and observe data from database
     */
    public FoodAndEntryViewModel(@NonNull Application application) {
        super(application);
        repository = new FoodAndEntryRepository(application);
        mAllFoodAndEntryLiveData = repository.getFoodAndEntryLiveData();
    }

    /*
        NOT UTILIZED
     */
    public LiveData<List<FoodAndEntry>> getFoodAndEntryLiveData(){
        return mAllFoodAndEntryLiveData;
    }

    /**
     * Returns JOINED RESULT SET for food and diary entry based off an ID
     * @param id pk and fk used to join the two tables
     * @return list of joined class object
     */
    public List<FoodAndEntry> getByIdList(int id){
        return mListFoodAndEntry = repository.getFoodAndEntryById(id);
    }
}
