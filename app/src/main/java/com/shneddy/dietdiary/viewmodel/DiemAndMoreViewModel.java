package com.shneddy.dietdiary.viewmodel;

import android.app.Application;

import com.shneddy.dietdiary.entity.Diem;
import com.shneddy.dietdiary.repository.DiemRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
/**
 * Created By Seth Sneddon Feb 2019
 */
public class DiemAndMoreViewModel extends AndroidViewModel {

    private DiemRepository repo;
    private LiveData<List<Diem>> liveDataList;

    /**
     * ViewModel for joined query results
     * @param application required to perform and observe data from database
     */
    public DiemAndMoreViewModel(@NonNull Application application) {
        super(application);
        repo = new DiemRepository(application);
    }

    /**
     * returns result set for joined query
     * @return livedata format of list with primary entity being Diem
     */
    public LiveData<List<Diem>> getLiveDataList(){
        return liveDataList;
    }
}
