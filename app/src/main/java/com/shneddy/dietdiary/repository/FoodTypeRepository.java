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

    /**
     * Constructor for repository
     * @param application context which is required to operate Room
     */
    public FoodTypeRepository(Application application) {
        FoodDiaryDatabase database = FoodDiaryDatabase.getInstance(application);
        foodTypeDAO = database.foodTypeDAO();
        allFoodTypes = foodTypeDAO.getAllFoodTypes();
        allFoodTypesList = foodTypeDAO.getAllFoodTypesList();
    }

    /**
     * Inserts food type asynchronously into the db
     * @param foodType to be inserted in db
     */
    public void insert(FoodType foodType){
        new InsertFoodTypesAsyncTask(foodTypeDAO).execute(foodType);
    }

    /**
     * updates a food type record asynchronously in the db
     * @param foodType to be updated in db
     */
    public void update(FoodType foodType){
        new UpdateFoodTypesAsyncTask(foodTypeDAO).execute(foodType);
    }

    /**
     * deletes a food type record asynchronously in the db
     * @param foodType to be deleted from db
     */
    public void delete(FoodType foodType){
        new DeleteFoodTypesAsyncTask(foodTypeDAO).execute(foodType);
    }

    /**
     * deletes all food type records from the database asynchronously
     */
    public void deleteAll(){
        new DeleteAllFoodTypesAsyncTask(foodTypeDAO).execute();
    }

    /**
     * returns list of foodtypes in livedata form
     * @return livedata which is returned
     */
    public LiveData<List<FoodType>> getAllFoodTypes(){
        return allFoodTypes;
    }

    /**
     * returns only list of food types
     * @return list of foodtypes
     */
    public List<FoodType> getAllFoodTypesList() {return allFoodTypesList; }

    /**
     * This static class performs the actual work of doing the insert asynchronously.
     */
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

    /**
     * This static class performs the actual work of updating asynchronously
     */
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

    /**
     * This static class performs the actual work of deletion asynchronously
     */
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

    /**
     * This static class performs the actual work of deleting all asynchronously
     */
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
