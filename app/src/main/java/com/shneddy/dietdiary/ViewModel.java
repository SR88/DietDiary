package com.shneddy.dietdiary;

import android.app.Application;

import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.entity.FoodDiary;
import com.shneddy.dietdiary.entity.FoodType;
import com.shneddy.dietdiary.entity.Phase;
import com.shneddy.dietdiary.repository.FoodDiaryRepository;
import com.shneddy.dietdiary.repository.FoodRepository;
import com.shneddy.dietdiary.repository.FoodTypeRepository;
import com.shneddy.dietdiary.repository.PhaseRepository;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ViewModel extends AndroidViewModel {

    private FoodRepository foodRepository;
    private FoodDiaryRepository foodDiaryRepository;
    private FoodTypeRepository foodTypeRepository;
    private PhaseRepository phaseRepository;

    private LiveData<List<Food>> allFoods;
    private LiveData<List<Phase>> allPhases;
    private LiveData<List<FoodDiary>> allFoodDiaries;
    private LiveData<List<FoodType>> allFoodTypes;

    /*
        constructor
     */
    public ViewModel(@NonNull Application application) {
        super(application);
        foodDiaryRepository = new FoodDiaryRepository(application);
        foodRepository = new FoodRepository(application);
        foodTypeRepository = new FoodTypeRepository(application);
        phaseRepository = new PhaseRepository(application);

        allFoods = foodRepository.getAllFoods();
        allFoodDiaries = foodDiaryRepository.getAllFoodDiaries();
        allPhases = phaseRepository.getAllPhases();
        allFoodTypes = foodTypeRepository.getAllFoodTypes();
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


    /*
        Food Diary Methods
     */
    public void insertFoodDiary(FoodDiary foodDiary){
        foodDiaryRepository.insert(foodDiary);
    }

    public void updateFoodDiary(FoodDiary foodDiary){
        foodDiaryRepository.update(foodDiary);
    }

    public void deleteFoodDiary(FoodDiary foodDiary){
        foodDiaryRepository.delete(foodDiary);
    }

    public void deleteAllFoodDiary(){
        foodDiaryRepository.deleteAll();
    }

    public LiveData<List<FoodDiary>> getAllFoodDiarys(){
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


    /*
        Phase Methods
    */
    public void insertPhase(Phase phase){
        phaseRepository.insert(phase);
    }

    public void updatePhase(Phase phase){
        phaseRepository.update(phase);
    }

    public void deletePhase(Phase phase){
        phaseRepository.delete(phase);
    }

    public void deleteAllPhases(){
        phaseRepository.deleteAll();
    }

    public LiveData<List<Phase>> getAllPhases(){
        return allPhases;
    }

}
