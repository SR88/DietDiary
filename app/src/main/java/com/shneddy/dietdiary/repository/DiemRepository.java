package com.shneddy.dietdiary.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.shneddy.dietdiary.dao.DiemDAO;
import com.shneddy.dietdiary.database.FoodDiaryDatabase;
import com.shneddy.dietdiary.entity.Diem;
import com.shneddy.dietdiary.entity.DiemAndEntry;

import java.util.List;

import androidx.lifecycle.LiveData;

public class DiemRepository {

    private DiemDAO dao;
    private List<Diem> listDiemByDate;
    private LiveData<List<Diem>> joinedData;
    private LiveData<List<Diem>> liveDataDiems;
    private List<DiemAndEntry> listDiemById;

    public DiemRepository(Application app) {
        FoodDiaryDatabase db = FoodDiaryDatabase.getInstance(app);
        dao = db.diemDAO();
        liveDataDiems = dao.getLiveDataDiem();
    }

    public List<Diem> getByDate(String stringDate){
        return listDiemByDate = dao.getByDateString(stringDate);
    }

    public LiveData<List<Diem>> getLiveById(){
        return joinedData = dao.getJoinedTables();
    }

    public List<DiemAndEntry> getJoinEntryById(int id){
        return listDiemById = dao.joinGetById(id);
    }



    public LiveData<List<Diem>> getLiveDataDiems(){
        return liveDataDiems;
    }

    public LiveData<List<Diem>> getJoinedData(){
        return joinedData;
    }

    public void insert(Diem diem){
        new InsertDiemAsyncTask(dao).execute(diem);
    }
    public void delete(Diem diem) {
        new DeleteDiemAsyncTask(dao).execute(diem);
    }
    public void update(Diem diem){
        new UpdateDiemAsyncTask(dao).execute(diem);
    }

    private class InsertDiemAsyncTask extends AsyncTask<Diem, Void, Void> {
        private DiemDAO dao;

        public InsertDiemAsyncTask(DiemDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Diem... diems) {
            dao.insert(diems[0]);
            return null;
        }
    }
    private class DeleteDiemAsyncTask extends AsyncTask<Diem, Void, Void>{
        private DiemDAO dao;

        public DeleteDiemAsyncTask(DiemDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Diem... diems) {
            dao.delete(diems[0]);
            return null;
        }
    }
    private class UpdateDiemAsyncTask extends AsyncTask<Diem, Void, Void> {
        private DiemDAO dao;

        public UpdateDiemAsyncTask(DiemDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Diem... diems) {
            dao.update(diems[0]);
            return null;
        }
    }
}
