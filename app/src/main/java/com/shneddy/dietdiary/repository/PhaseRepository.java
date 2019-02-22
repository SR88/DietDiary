package com.shneddy.dietdiary.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.shneddy.dietdiary.dao.PhaseDAO;
import com.shneddy.dietdiary.database.FoodDiaryDatabase;
import com.shneddy.dietdiary.entity.Phase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class PhaseRepository {

    private PhaseDAO phaseDAO;
    private LiveData<List<Phase>> allPhases;
    

    public PhaseRepository(Application application) {
        FoodDiaryDatabase database = FoodDiaryDatabase.getInstance(application);
        phaseDAO = database.phaseDAO();
        allPhases = phaseDAO.getAllPhaseTypes();
    }

    public void insert(Phase food){
        new InsertPhasesAsyncTask(phaseDAO).execute(food);
    }

    public void update(Phase food){
        new UpdatePhasesAsyncTask(phaseDAO).execute(food);
    }

    public void delete(Phase food){
        new DeletePhasesAsyncTask(phaseDAO).execute(food);
    }

    public void deleteAll(){
        new DeleteAllPhasesAsyncTask(phaseDAO).execute();
    }

    public LiveData<List<Phase>> getAllPhases(){
        return allPhases;
    }

    private static class InsertPhasesAsyncTask extends AsyncTask<Phase, Void, Void> {

        private PhaseDAO phaseDAO;

        private InsertPhasesAsyncTask(PhaseDAO phaseDAO){
            this.phaseDAO = phaseDAO;
        }

        @Override
        protected Void doInBackground(Phase... phases) {
            phaseDAO.insert(phases[0]);
            return null;
        }
    }
    private static class UpdatePhasesAsyncTask extends AsyncTask<Phase, Void, Void>{

        private PhaseDAO phaseDAO;

        private UpdatePhasesAsyncTask(PhaseDAO phaseDAO){
            this.phaseDAO = phaseDAO;
        }

        @Override
        protected Void doInBackground(Phase... phases) {
            phaseDAO.update(phases[0]);
            return null;
        }
    }
    private static class DeletePhasesAsyncTask extends AsyncTask<Phase, Void, Void>{

        private PhaseDAO phaseDAO;

        private DeletePhasesAsyncTask(PhaseDAO phaseDAO){
            this.phaseDAO = phaseDAO;
        }

        @Override
        protected Void doInBackground(Phase... phases) {
            phaseDAO.delete(phases[0]);
            return null;
        }
    }
    private static class DeleteAllPhasesAsyncTask extends AsyncTask<Void, Void, Void>{

        private PhaseDAO phaseDAO;

        private DeleteAllPhasesAsyncTask(PhaseDAO phaseDAO){
            this.phaseDAO = phaseDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            phaseDAO.deleteAllPhaseTypes();
            return null;
        }
    }
    
}
