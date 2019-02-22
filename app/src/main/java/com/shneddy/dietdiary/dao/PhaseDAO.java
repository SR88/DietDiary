package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.Phase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PhaseDAO {

    @Insert
    void insert(Phase phase);

    @Update
    void update(Phase phase);

    @Delete
    void delete(Phase phase);

    @Query("DELETE FROM food_phase")
    void deleteAllPhaseTypes();

    @Query("SELECT * FROM food_phase")
    LiveData<List<Phase>> getAllPhaseTypes();

}
