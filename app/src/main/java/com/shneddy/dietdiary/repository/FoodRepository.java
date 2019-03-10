package com.shneddy.dietdiary.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.shneddy.dietdiary.dao.FoodDAO;
import com.shneddy.dietdiary.dao.DiaryEntryDAO;
import com.shneddy.dietdiary.dao.FoodTypeDAO;
import com.shneddy.dietdiary.database.FoodDiaryDatabase;
import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.entity.DiaryEntry;
import com.shneddy.dietdiary.entity.FoodAndType;
import com.shneddy.dietdiary.entity.FoodType;

import java.util.List;
import androidx.lifecycle.LiveData;


public class FoodRepository {

    private FoodDAO foodDAO;
    private DiaryEntryDAO diaryEntryDAO;
    private FoodTypeDAO foodTypeDAO;
    private LiveData<List<FoodType>> allFoodTypes;
    private LiveData<List<Food>> allFoods;
    private LiveData<List<DiaryEntry>> allFoodDiary;
    private LiveData<List<FoodAndType>> allFoodsAndTypes;
    private Food foodById;


    public FoodRepository(Application application) {
        FoodDiaryDatabase database = FoodDiaryDatabase.getInstance(application);
        foodDAO = database.foodDAO();
        allFoods = foodDAO.getAllFoods();

        diaryEntryDAO = database.entryDAO();
        allFoodDiary = diaryEntryDAO.getAllDiaryEntries();

        foodTypeDAO = database.foodTypeDAO();
        allFoodTypes = foodTypeDAO.getAllFoodTypes();

        allFoodsAndTypes = foodDAO.foodsAndTypesList();
    }

    public void insert(Food food){
        new InsertFoodsAsyncTask(foodDAO).execute(food);
    }

    public void update(Food food){
        new UpdateFoodsAsyncTask(foodDAO).execute(food);
    }

    public void delete(Food food){
        new DeleteFoodsAsyncTask(foodDAO).execute(food);
    }

    public void deleteAll(){
        new DeleteAllFoodsAsyncTask(foodDAO).execute();
    }

    public LiveData<List<Food>> getAllFoods(){
        return allFoods;
    }

    public LiveData<List<FoodAndType>> getAllFoodsAndTypes(){
        return allFoodsAndTypes;
    }

    public Food getFoodById(int id){
        return foodById = foodDAO.getFoodById(id);
    }

    private static class InsertFoodsAsyncTask extends AsyncTask<Food, Void, Void>{

        private FoodDAO foodDAO;

        private InsertFoodsAsyncTask(FoodDAO foodDAO){
            this.foodDAO = foodDAO;
        }

        @Override
        protected Void doInBackground(Food... foods) {
            foodDAO.insert(foods[0]);
            return null;
        }
    }
    private static class UpdateFoodsAsyncTask extends AsyncTask<Food, Void, Void>{

        private FoodDAO foodDAO;

        private UpdateFoodsAsyncTask(FoodDAO foodDAO){
            this.foodDAO = foodDAO;
        }

        @Override
        protected Void doInBackground(Food... foods) {
            foodDAO.update(foods[0]);
            return null;
        }
    }
    private static class DeleteFoodsAsyncTask extends AsyncTask<Food, Void, Void>{

        private FoodDAO foodDAO;

        private DeleteFoodsAsyncTask(FoodDAO foodDAO){
            this.foodDAO = foodDAO;
        }

        @Override
        protected Void doInBackground(Food... foods) {
            foodDAO.delete(foods[0]);
            return null;
        }
    }
    private static class DeleteAllFoodsAsyncTask extends AsyncTask<Void, Void, Void>{

        private FoodDAO foodDAO;

        private DeleteAllFoodsAsyncTask(FoodDAO foodDAO){
            this.foodDAO = foodDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            foodDAO.deleteAllFoods();
            return null;
        }
    }

}
