package com.shneddy.dietdiary.viewmodel;

import android.app.Application;

import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.repository.FoodRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class SearchViewModel extends ViewModel {

    private final MutableLiveData<String> query = new MutableLiveData<>();

    public final LiveData<List<Food>> results;

    private FoodRepository repository;

    SearchViewModel(Application application){
        this.repository = new FoodRepository(application);
        results = Transformations.switchMap(query, search -> {
            return repository.search(search);
        });
    }

    public SearchViewModel() {
        results = Transformations.switchMap(query, search -> {
            return repository.search(search);
        });
    }

    private void searchFoods(String searchString){
        repository.search(searchString);
    }

    public void setQuery(String query){
        this.query.setValue(query);
    }

}
