package com.shneddy.dietdiary.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.shneddy.dietdiary.dao.FoodTypeDAO;
import com.shneddy.dietdiary.database.FoodDiaryDatabase;
import com.shneddy.dietdiary.entity.FoodType;

import java.util.List;

import androidx.lifecycle.LiveData;
/**
 * Created By Seth Sneddon Feb 2019
 */
public class FoodTypeRepository {

    private FoodTypeDAO foodTypeDAO;
    private LiveData<List<FoodType>> allFoodTypes;
    private List<FoodType> allFoodTypesList;

    public FoodTypeRepository(Application application) {
        FoodDiaryDatabase database = FoodDiaryDatabase.getInstance(application);
        foodTypeDAO = database.foodTypeDAO();
        allFoodTypes = foodTypeDAO.getAllFoodTypes();
        allFoodTypesList = foodTypeDAO.getAllFoodTypesList();
    }

    public void insert(FoodType foodType){
        new InsertFoodTypesAsyncTask(foodTypeDAO).execute(foodType);
    }

    public void update(FoodType foodType){
        new UpdateFoodTypesAsyncTask(foodTypeDAO).execute(foodType);
    }

    public void delete(FoodType foodType){
        new DeleteFoodTypesAsyncTask(foodTypeDAO).execute(foodType);
    }

    public void deleteAll(){
        new DeleteAllFoodTypesAsyncTask(foodTypeDAO).execute();
    }

    public LiveData<List<FoodType>> getAllFoodTypes(){
        return allFoodTypes;
    }

    public List<FoodType> getAllFoodTypesList() {return allFoodTypesList; }

    private static class InsertFoodTypesAsyncTask extends AsyncTask<FoodType, Void, Void> {

        private FoodTypeDAO foodTypeDAO;

        private InsertFoodTypesAsyncTask(FoodTypeDAO foodTypeDAO){
            this.foodTypeDAO = foodTypeDAO;
        }

        @Override
        protected Void doInBackground(FoodType... foodTypes) {
            foodTypeDAO.insert(foodTypes[0]);
            return null;
        }
    }
    private static class UpdateFoodTypesAsyncTask extends AsyncTask<FoodType, Void, Void>{

        private FoodTypeDAO foodTypeDAO;

        private UpdateFoodTypesAsyncTask(FoodTypeDAO foodTypeDAO){
            this.foodTypeDAO = foodTypeDAO;
        }

        @Override
        protected Void doInBackground(FoodType... foodTypes) {
            foodTypeDAO.update(foodTypes[0]);
            return null;
        }
    }
    private static class DeleteFoodTypesAsyncTask extends AsyncTask<FoodType, Void, Void>{

        private FoodTypeDAO foodTypeDAO;

        private DeleteFoodTypesAsyncTask(FoodTypeDAO foodTypeDAO){
            this.foodTypeDAO = foodTypeDAO;
        }

        @Override
        protected Void doInBackground(FoodType... foodTypes) {
            foodTypeDAO.delete(foodTypes[0]);
            return null;
        }
    }
    private static class DeleteAllFoodTypesAsyncTask extends AsyncTask<Void, Void, Void>{

        private FoodTypeDAO foodTypeDAO;

        private DeleteAllFoodTypesAsyncTask(FoodTypeDAO foodTypeDAO){
            this.foodTypeDAO = foodTypeDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            foodTypeDAO.deleteAllFoodTypes();
            return null;
        }
    }
    
}
