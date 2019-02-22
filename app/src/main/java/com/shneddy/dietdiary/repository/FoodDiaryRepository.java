package com.shneddy.dietdiary.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.shneddy.dietdiary.dao.FoodDiaryDAO;
import com.shneddy.dietdiary.database.FoodDiaryDatabase;
import com.shneddy.dietdiary.entity.FoodDiary;

import java.util.List;

import androidx.lifecycle.LiveData;

public class FoodDiaryRepository {
    
    private FoodDiaryDAO foodDiaryDAO;
    private LiveData<List<FoodDiary>> allFoodDiary;

    public FoodDiaryRepository(Application application) {
        FoodDiaryDatabase database = FoodDiaryDatabase.getInstance(application);
        foodDiaryDAO = database.foodDiaryDAO();
        allFoodDiary = foodDiaryDAO.getAllDiaryEntries();
    }

    public void insert(FoodDiary foodDiary){
        new InsertFoodDiariesAsyncTask(foodDiaryDAO).execute(foodDiary);
    }

    public void update(FoodDiary foodDiary){
        new UpdateFoodDiariesAsyncTask(foodDiaryDAO).execute(foodDiary);
    }

    public void delete(FoodDiary foodDiary){
        new DeleteFoodDiariesAsyncTask(foodDiaryDAO).execute(foodDiary);
    }

    public void deleteAll(){
        new DeleteAllFoodDiariesAsyncTask(foodDiaryDAO).execute();
    }

    public LiveData<List<FoodDiary>> getAllFoodDiaries(){
        return allFoodDiary;
    }

    private static class InsertFoodDiariesAsyncTask extends AsyncTask<FoodDiary, Void, Void>{

        private FoodDiaryDAO foodDiaryDAO;

        private InsertFoodDiariesAsyncTask(FoodDiaryDAO foodDiaryDAO){
            this.foodDiaryDAO = foodDiaryDAO;
        }

        @Override
        protected Void doInBackground(FoodDiary... foods) {
            foodDiaryDAO.insert(foods[0]);
            return null;
        }
    }
    private static class UpdateFoodDiariesAsyncTask extends AsyncTask<FoodDiary, Void, Void> {

        private FoodDiaryDAO foodDiaryDAO;

        private UpdateFoodDiariesAsyncTask(FoodDiaryDAO foodDiaryDAO){
            this.foodDiaryDAO = foodDiaryDAO;
        }

        @Override
        protected Void doInBackground(FoodDiary... foods) {
            foodDiaryDAO.update(foods[0]);
            return null;
        }
    }
    private static class DeleteFoodDiariesAsyncTask extends AsyncTask<FoodDiary, Void, Void>{

        private FoodDiaryDAO foodDiaryDAO;

        private DeleteFoodDiariesAsyncTask(FoodDiaryDAO foodDiaryDAO){
            this.foodDiaryDAO = foodDiaryDAO;
        }

        @Override
        protected Void doInBackground(FoodDiary... foodDiaries) {
            foodDiaryDAO.delete(foodDiaries[0]);
            return null;
        }
    }
    private static class DeleteAllFoodDiariesAsyncTask extends AsyncTask<Void, Void, Void>{

        private FoodDiaryDAO foodDiaryDAO;

        private DeleteAllFoodDiariesAsyncTask(FoodDiaryDAO foodDiaryDAO){
            this.foodDiaryDAO = foodDiaryDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            foodDiaryDAO.deleteAllFoodTypes();
            return null;
        }
    }
    
}
