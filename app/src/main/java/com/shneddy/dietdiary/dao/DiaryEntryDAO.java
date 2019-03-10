package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.DiaryEntry;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DiaryEntryDAO {

    @Insert
    void insert(DiaryEntry diaryEntry);

    @Update
    void update(DiaryEntry diaryEntry);

    @Delete
    void delete(DiaryEntry diaryEntry);

    @Query("DELETE FROM DiaryEntry")
    void deleteAllFoodTypes();

    @Query("SELECT * FROM DiaryEntry")
    LiveData<List<DiaryEntry>> getAllDiaryEntries();

    @Query("SELECT * FROM DiaryEntry")
    List<DiaryEntry> getAllEntriesList();

    @Query("SELECT * FROM DiaryEntry where diemId = :id")
    List<DiaryEntry> getAllEntriesListByDiemId(int id);

    @Query("Select * from diaryentry where diemId = :id")
    LiveData<List<DiaryEntry>> getEntriesByDiemId(int id);
}
