package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.Diem;
import com.shneddy.dietdiary.entity.DiemAndEntry;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DiemDAO {

    @Insert
    void insert(Diem diem);

    @Update
    void update(Diem diem);

    @Delete
    void delete(Diem diem);

    @Query("Select * from diem where diem.date = :date")
    List<Diem> getByDateString(String date);

    @Query("Select * from diem where diem.id = :id")
    List<DiemAndEntry> joinGetById(int id);

    @Query("Select * from diem")
    LiveData<List<Diem>> getLiveDataDiem();

    @Query("Select * from diem join diaryentry on diaryentry.diemId=diem.id inner join food on diaryentry.foodId=food.id ")
    LiveData<List<Diem>> getJoinedTables();
}
