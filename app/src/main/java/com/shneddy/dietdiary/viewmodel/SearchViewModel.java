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

    public SearchViewModel(Application application) {
        super(application);
        repository = new FoodRepository(application);
        results = Transformations.switchMap(query, (newTerm) -> {
            return repository.search(newTerm);
        });
    }

    public LiveData<List<Food>> search(String searchTerm){
        if(searchTerm != null) query.setValue(searchTerm);
        return results;
    }

    public String getSearchTermString(){
        return query.getValue();
    }

    public void setRepository(FoodRepository repository) {
        this.repository = repository;
    }

    public void setApplication(Application application){
        this.repository = new FoodRepository(application);
    }



    //    SearchViewModel(Application application){
//        this.repository = new FoodRepository(application);
//        results = Transformations.switchMap(query, search -> {
//            return repository.search(search);
//        });
//    }
//
//    public SearchViewModel() {
//        results = Transformations.switchMap(query, search -> {
//            return repository.search(search);
//        });
//    }
//
//    private void searchFoods(String searchString){
//        repository.search(searchString);
//    }
//
//    public void setQuery(String query){
//        this.query.setValue(query);
//    }

}
