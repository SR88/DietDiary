package com.shneddy.dietdiary.viewmodel;

import android.app.Application;

import com.shneddy.dietdiary.entity.DiaryEntry;
import com.shneddy.dietdiary.entity.Diem;
import com.shneddy.dietdiary.entity.DiemAndEntry;
import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.entity.FoodAndType;
import com.shneddy.dietdiary.entity.FoodType;
import com.shneddy.dietdiary.repository.DiaryEntryRepository;
import com.shneddy.dietdiary.repository.DiemRepository;
import com.shneddy.dietdiary.repository.FoodRepository;
import com.shneddy.dietdiary.repository.FoodTypeRepository;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class OperationsViewModel extends AndroidViewModel {

    private FoodRepository foodRepository;
    private DiaryEntryRepository diaryEntryRepository;
    private FoodTypeRepository foodTypeRepository;
    private DiemRepository diemRepository;

    private LiveData<List<Food>> allFoods;
    private List<Food> allFoodsList;
    private List<Food> searchFoodList;

    private LiveData<List<DiaryEntry>> allFoodDiaries;
    private LiveData<List<DiaryEntry>> allEntriesByDiemId;
    private List<DiaryEntry> listEntriesByDiemId;

    private LiveData<List<FoodType>> allFoodTypes;
    private List<FoodType> allFoodTypesList;
    private LiveData<List<FoodAndType>> allFoodsAndType;

    private List<Diem> listDiemByDate;
    private List<DiemAndEntry> listDiemById;
    private LiveData<List<Diem>> allDiems;

    private Food foodById;

    /*
        constructor
     */
    public OperationsViewModel(@NonNull Application application) {
        super(application);
        diaryEntryRepository = new DiaryEntryRepository(application);
        foodRepository = new FoodRepository(application);
        foodTypeRepository = new FoodTypeRepository(application);
        diemRepository = new DiemRepository(application);

        allFoods = foodRepository.getAllFoods();
        allFoodsList = foodRepository.getGetAllFoodsList();
        allFoodsAndType = foodRepository.getAllFoodsAndTypes();
        allFoodDiaries = diaryEntryRepository.getAllFoodEntries();
        allFoodTypes = foodTypeRepository.getAllFoodTypes();
        allFoodTypesList = foodTypeRepository.getAllFoodTypesList();
        allDiems = diemRepository.getLiveDataDiems();
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

    public List<Food> getAllFoodsList(){
        return allFoodsList;
    }

    public LiveData<List<FoodAndType>> getAllFoodsAndTypes() {
        return allFoodsAndType;
    }

    public Food getFoodById(int id){
        return foodById = foodRepository.getFoodById(id);
    }

    public List<Food> searchForFoodsByString(String searchTerm){
        return searchFoodList = foodRepository.searchForFood(searchTerm);
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

    public LiveData<List<DiaryEntry>> getAllEntriesByDiemId(int id) {
        return allEntriesByDiemId = diaryEntryRepository.getEntriesByDiemId(id);
    }

    public List<DiaryEntry> getListEntriesByDiemId(int id){
        return listEntriesByDiemId = diaryEntryRepository.getAllEntriesListByDiemId(id);
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


    /*
        Diem Methods
     */
    public void insertDiem(Diem diem){
        diemRepository.insert(diem);
    }

    public void updateDiem(Diem diem){
        diemRepository.update(diem);
    }

    public void deleteDiem(Diem diem){
        diemRepository.delete(diem);
    }

    public List<Diem> getDiemByDate(String date){
        return listDiemByDate = diemRepository.getByDate(date);
    }

    public List<DiemAndEntry> getDiemById(int id){
        return listDiemById = diemRepository.getJoinEntryById(id);
    }

    public LiveData<List<Diem>> getAllDiems() {
        return allDiems;
    }

    public void deleteAllDiem(){
        diemRepository.deleteAll();
    }
}
