package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.Diem;

import java.util.List;

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

}
