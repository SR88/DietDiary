package com.shneddy.dietdiary.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.shneddy.dietdiary.dao.DiaryEntryDAO;
import com.shneddy.dietdiary.database.FoodDiaryDatabase;
import com.shneddy.dietdiary.entity.DiaryEntry;

import java.util.List;

import androidx.lifecycle.LiveData;
/**
 * Created By Seth Sneddon Feb 2019
 */
public class DiaryEntryRepository {
    
    private DiaryEntryDAO diaryEntryDAO;
    private LiveData<List<DiaryEntry>> allFoodDiary;
    private List<DiaryEntry> entryList;
    private List<DiaryEntry> entryListByDiemId;
    private LiveData<List<DiaryEntry>> listLiveDataByDiemId;

    public DiaryEntryRepository(Application application) {
        FoodDiaryDatabase database = FoodDiaryDatabase.getInstance(application);
        diaryEntryDAO = database.entryDAO();
        allFoodDiary = diaryEntryDAO.getAllDiaryEntries();
    }

    public void insert(DiaryEntry diaryEntry){
        new InsertFoodDiariesAsyncTask(diaryEntryDAO).execute(diaryEntry);
    }

    public void update(DiaryEntry diaryEntry){
        new UpdateFoodDiariesAsyncTask(diaryEntryDAO).execute(diaryEntry);
    }

    public void delete(DiaryEntry diaryEntry){
        new DeleteFoodDiariesAsyncTask(diaryEntryDAO).execute(diaryEntry);
    }

    public void deleteAll(){
        new DeleteAllFoodDiariesAsyncTask(diaryEntryDAO).execute();
    }

    public LiveData<List<DiaryEntry>> getAllFoodEntries(){
        return allFoodDiary;
    }

    public LiveData<List<DiaryEntry>> getEntriesByDiemId(int id){
        return listLiveDataByDiemId = diaryEntryDAO.getEntriesByDiemId(id);
    }

    public List<DiaryEntry> getAllEntriesList(){
        return entryList;
    }

    public List<DiaryEntry> getAllEntriesListByDiemId(int id){
        return entryListByDiemId = diaryEntryDAO.getAllEntriesListByDiemId(id);
    }



    private static class InsertFoodDiariesAsyncTask extends AsyncTask<DiaryEntry, Void, Void>{

        private DiaryEntryDAO diaryEntryDAO;

        private InsertFoodDiariesAsyncTask(DiaryEntryDAO diaryEntryDAO){
            this.diaryEntryDAO = diaryEntryDAO;
        }

        @Override
        protected Void doInBackground(DiaryEntry... foods) {
            diaryEntryDAO.insert(foods[0]);
            return null;
        }
    }
    private static class UpdateFoodDiariesAsyncTask extends AsyncTask<DiaryEntry, Void, Void> {

        private DiaryEntryDAO diaryEntryDAO;

        private UpdateFoodDiariesAsyncTask(DiaryEntryDAO diaryEntryDAO){
            this.diaryEntryDAO = diaryEntryDAO;
        }

        @Override
        protected Void doInBackground(DiaryEntry... foods) {
            diaryEntryDAO.update(foods[0]);
            return null;
        }
    }
    private static class DeleteFoodDiariesAsyncTask extends AsyncTask<DiaryEntry, Void, Void>{

        private DiaryEntryDAO diaryEntryDAO;

        private DeleteFoodDiariesAsyncTask(DiaryEntryDAO diaryEntryDAO){
            this.diaryEntryDAO = diaryEntryDAO;
        }

        @Override
        protected Void doInBackground(DiaryEntry... foodDiaries) {
            diaryEntryDAO.delete(foodDiaries[0]);
            return null;
        }
    }
    private static class DeleteAllFoodDiariesAsyncTask extends AsyncTask<Void, Void, Void>{

        private DiaryEntryDAO diaryEntryDAO;

        private DeleteAllFoodDiariesAsyncTask(DiaryEntryDAO diaryEntryDAO){
            this.diaryEntryDAO = diaryEntryDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            diaryEntryDAO.deleteAllFoodTypes();
            return null;
        }
    }
    
}
