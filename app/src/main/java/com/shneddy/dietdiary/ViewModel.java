package com.shneddy.dietdiary;

import android.app.Application;

import com.shneddy.dietdiary.entity.DiaryEntry;
import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.entity.FoodAndType;
import com.shneddy.dietdiary.entity.FoodAndTypeData;
import com.shneddy.dietdiary.entity.FoodType;
import com.shneddy.dietdiary.repository.DiaryEntryRepository;
import com.shneddy.dietdiary.repository.FoodRepository;
import com.shneddy.dietdiary.repository.FoodTypeRepository;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ViewModel extends AndroidViewModel {

    private FoodRepository foodRepository;
    private DiaryEntryRepository diaryEntryRepository;
    private FoodTypeRepository foodTypeRepository;

    private LiveData<List<Food>> allFoods;
    private LiveData<List<DiaryEntry>> allFoodDiaries;
    private LiveData<List<FoodType>> allFoodTypes;
    private List<FoodType> allFoodTypesList;
    private LiveData<List<FoodAndType>> allFoodsAndType;

    /*
        constructor
     */
    public ViewModel(@NonNull Application application) {
        super(application);
        diaryEntryRepository = new DiaryEntryRepository(application);
        foodRepository = new FoodRepository(application);
        foodTypeRepository = new FoodTypeRepository(application);


        allFoods = foodRepository.getAllFoods();
        allFoodsAndType = foodRepository.getAllFoodsAndTypes();
        allFoodDiaries = diaryEntryRepository.getAllFoodEntries();
        allFoodTypes = foodTypeRepository.getAllFoodTypes();
        allFoodTypesList = foodTypeRepository.getAllFoodTypesList();

    }


    /*
        Food Methods
     */
    public void insertFood(Food food){
        foodRepository.insert(food);
    }

    public void updateFood(Food food){
        foodRepository.update(food);
    }

    public void deleteFood(Food food){
        foodRepository.delete(food);
    }

    public void deleteAllFood(){
        foodRepository.deleteAll();
    }

    public LiveData<List<Food>> getAllFoods(){
        return allFoods;
    }

    public LiveData<List<FoodAndType>> getAllFoodsAndTypes() {
        return allFoodsAndType;
    }

    /*
        Diary Entry Methods
     */
    public void insertFoodDiary(DiaryEntry diaryEntry){
        diaryEntryRepository.insert(diaryEntry);
    }

    public void updateFoodDiary(DiaryEntry diaryEntry){
        diaryEntryRepository.update(diaryEntry);
    }

    public void deleteFoodDiary(DiaryEntry diaryEntry){
        diaryEntryRepository.delete(diaryEntry);
    }

    public void deleteAllFoodDiary(){
        diaryEntryRepository.deleteAll();
    }

    public LiveData<List<DiaryEntry>> getAllFoodDiarys(){
        return allFoodDiaries;
    }

    /*
        Food Type Methods
    */
    public void insertFoodType(FoodType foodType){
        foodTypeRepository.insert(foodType);
    }

    public void updateFoodType(FoodType foodType){
        foodTypeRepository.update(foodType);
    }

    public void deleteFoodType(FoodType foodType){
        foodTypeRepository.delete(foodType);
    }

    public void deleteAllFoodTypes(){
        foodTypeRepository.deleteAll();
    }

    public LiveData<List<FoodType>> getAllFoodTypes(){
        return allFoodTypes;
    }

    public List<FoodType> getAllFoodTypesList() {return allFoodTypesList; }


}
