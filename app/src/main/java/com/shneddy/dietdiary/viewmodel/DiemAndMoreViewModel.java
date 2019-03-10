package com.shneddy.dietdiary.viewmodel;

import android.app.Application;

import com.shneddy.dietdiary.entity.Diem;
import com.shneddy.dietdiary.repository.DiemRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class DiemAndMoreViewModel extends AndroidViewModel {

    private DiemRepository repo;
    private LiveData<List<Diem>> liveDataList;

    public DiemAndMoreViewModel(@NonNull Application application) {
        super(application);
        repo = new DiemRepository(application);
    }

    public LiveData<List<Diem>> getLiveDataList(){
        return liveDataList;
    }
}
