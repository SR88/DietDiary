package com.shneddy.dietdiary.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.shneddy.dietdiary.dao.DiemDAO;
import com.shneddy.dietdiary.database.FoodDiaryDatabase;
import com.shneddy.dietdiary.entity.Diem;

import java.util.List;

public class DiemRepository {

    private DiemDAO dao;
    private List<Diem> listDiemByDate;

    public DiemRepository(Application app) {
        FoodDiaryDatabase db = FoodDiaryDatabase.getInstance(app);
        dao = db.diemDAO();
    }

    public List<Diem> getByDate(String stringDate){
        return listDiemByDate = dao.getByDateString(stringDate);
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
