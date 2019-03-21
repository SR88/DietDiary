package com.shneddy.dietdiary.viewmodel;

import android.app.Application;

import com.shneddy.dietdiary.database.FoodDiaryDatabase;
import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.repository.FoodRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
/**
 * Created By Seth Sneddon Feb 2019
 */
public class SearchViewModel extends AndroidViewModel {

    private final MutableLiveData<String> query = new MutableLiveData<>();
    public final LiveData<List<Food>> results;
    private FoodRepository repository;

    /**
     * Sets up view model and gets singleton of database
     * @param application
     */
    public SearchViewModel(Application application) {
        super(application);
        repository = new FoodRepository(application);
        results = Transformations.switchMap(query, (newTerm) -> {
            return repository.search(newTerm);
        });
    }

    /**
     * Searches database based on keywords in food names
     * @param searchTerm key word to search for
     * @return livedata list of foods matching the searchterm
     */
    public LiveData<List<Food>> search(String searchTerm){
        if(searchTerm != null) query.setValue(searchTerm);
        return results;
    }

    /*
        NOT UTILIZED
     */
    public String getSearchTermString(){
        return query.getValue();
    }

    /**
     * Sets repository up for this class
     * @param repository to be utilized in the class
     */
    public void setRepository(FoodRepository repository) {
        this.repository = repository;
    }

    /**
     * Sets up application variable in this class
     * @param application
     */
    public void setApplication(Application application){
        this.repository = new FoodRepository(application);
    }

}
