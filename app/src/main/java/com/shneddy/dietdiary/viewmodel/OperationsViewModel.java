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
/**
 * Created By Seth Sneddon Feb 2019
 */
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

    /**
     * Main operational view model for the application. Get's results for all of the activities'
     * queries to the database
     * @param application instance needed to instantiate the database singleton
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

    /**
     * Inserts a food into the database
     * @param food to be inserted
     */
    public void insertFood(Food food){
        foodRepository.insert(food);
    }

    /**
     * Updates a food record in the database
     * @param food to be updated
     */
    public void updateFood(Food food){
        foodRepository.update(food);
    }

    /**
     * deletes a food in the database
     * @param food to be delete
     */
    public void deleteFood(Food food){
        foodRepository.delete(food);
    }

    /**
     * Deletes all foods in the database
     */
    public void deleteAllFood(){
        foodRepository.deleteAll();
    }

    /**
     * Returns livedata list of all foods in the database
     * @return livedata list of all foods
     */
    public LiveData<List<Food>> getAllFoods(){
        return allFoods;
    }

    /**
     * Returns list of all foods in the database
     * @return list of all foods
     */
    public List<Food> getAllFoodsList(){
        return allFoodsList;
    }

    /**
     * Returns live data list of JOINED food and food type data
     * @return livedata list of joined tables
     */
    public LiveData<List<FoodAndType>> getAllFoodsAndTypes() {
        return allFoodsAndType;
    }

    /**
     * Queries database for food record by id
     * @param id for a food recrod
     * @return a food entity matching that in the database
     */
    public Food getFoodById(int id){
        return foodById = foodRepository.getFoodById(id);
    }

    /**
     * Searches database for foods based on name of food
     * @param searchTerm via user input
     * @return list of foods matching search term
     */
    public List<Food> searchForFoodsByString(String searchTerm){
        return searchFoodList = foodRepository.searchForFood(searchTerm);
    }

    /*
        Diary Entry Methods
     */

    /**
     * Inserts diary entry into the database
     * @param diaryEntry to be inserted
     */
    public void insertFoodDiary(DiaryEntry diaryEntry){
        diaryEntryRepository.insert(diaryEntry);
    }

    /**
     * updates a diary entry in the database
     * @param diaryEntry to be updated
     */
    public void updateFoodDiary(DiaryEntry diaryEntry){
        diaryEntryRepository.update(diaryEntry);
    }

    /**
     * deletes a diary entry in the database
     * @param diaryEntry
     */
    public void deleteFoodDiary(DiaryEntry diaryEntry){
        diaryEntryRepository.delete(diaryEntry);
    }

    /**
     * deletes all diary entries in the database
     */
    public void deleteAllFoodDiary(){
        diaryEntryRepository.deleteAll();
    }

    /**
     * Get all diary entries from the database
     * @return livedata list of entries
     */
    public LiveData<List<DiaryEntry>> getAllFoodDiarys(){
        return allFoodDiaries;
    }

    /**
     * Get all entires with diem id of a particular value
     * @param id of diem to search on
     * @return livedata list of diary entries
     */
    public LiveData<List<DiaryEntry>> getAllEntriesByDiemId(int id) {
        return allEntriesByDiemId = diaryEntryRepository.getEntriesByDiemId(id);
    }

    /**
     * get list of diary entires by a particular diem id value
     * @param id
     * @return
     */
    public List<DiaryEntry> getListEntriesByDiemId(int id){
        return listEntriesByDiemId = diaryEntryRepository.getAllEntriesListByDiemId(id);
    }

    /*
        Food Type Methods
    */

    /**
     * inserts a foodtype into the database
     * @param foodType to be inserted
     */
    public void insertFoodType(FoodType foodType){
        foodTypeRepository.insert(foodType);
    }

    /**
     * updates a particular record in the database
     * @param foodType record to be updated
     */
    public void updateFoodType(FoodType foodType){
        foodTypeRepository.update(foodType);
    }

    /**
     * deletes a particular foodtype in the database
     * @param foodType to be deleted
     */
    public void deleteFoodType(FoodType foodType){
        foodTypeRepository.delete(foodType);
    }

    /**
     * deletes all foodtypes in the database
     */
    public void deleteAllFoodTypes(){
        foodTypeRepository.deleteAll();
    }

    /**
     * gets all food types in the database
     * @return livedata list of all foodtypes
     */
    public LiveData<List<FoodType>> getAllFoodTypes(){
        return allFoodTypes;
    }

    /**
     * gets all foodtypes in the database in list form
     * @return list of all foodtypes in the database
     */
    public List<FoodType> getAllFoodTypesList() {return allFoodTypesList; }


    /*
        Diem Methods
     */
    /**
     * inserts a diem into the database
     * @param diem
     */
    public void insertDiem(Diem diem){
        diemRepository.insert(diem);
    }

    /**
     * updates a particular diem in the database
     * @param diem to be updated
     */
    public void updateDiem(Diem diem){
        diemRepository.update(diem);
    }

    /**
     * deletes a particular diem in the database
     * @param diem to be deleted
     */
    public void deleteDiem(Diem diem){
        diemRepository.delete(diem);
    }

    /**
     * returns a list of diems that match a particular string
     * @param date string used to perform the query
     * @return list of diems in the result set
     */
    public List<Diem> getDiemByDate(String date){
        return listDiemByDate = diemRepository.getByDate(date);
    }

    /**
     * Gets a JOIN result set of diems and diary entries where an id is shared
     * @param id used to query the database
     * @return list of
     */
    public List<DiemAndEntry> getDiemById(int id){
        return listDiemById = diemRepository.getJoinEntryById(id);
    }

    /**
     * Gets all Diems in the database
     * @return returns live data list of all diems
     */
    public LiveData<List<Diem>> getAllDiems() {
        return allDiems;
    }

    /**
     * Deletes all diems in the database
     */
    public void deleteAllDiem(){
        diemRepository.deleteAll();
    }
}
