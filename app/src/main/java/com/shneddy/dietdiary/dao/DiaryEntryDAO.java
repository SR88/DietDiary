package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.DiaryEntry;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
/**
 * Created By Seth Sneddon Feb 2019
 */
@Dao
public interface DiaryEntryDAO {

    /**
     * Inserts record into database
     * @param diaryEntry
     */
    @Insert
    void insert(DiaryEntry diaryEntry);

    /**
     * Updates record in database
     * @param diaryEntry
     */
    @Update
    void update(DiaryEntry diaryEntry);

    /**
     * Deletes record in database
     * @param diaryEntry
     */
    @Delete
    void delete(DiaryEntry diaryEntry);

    /**
     * Deletes all diary entries from database
     */
    @Query("DELETE FROM DiaryEntry")
    void deleteAllFoodTypes();

    /**
     * Gets all diary entries from db
     * @return Live data list
     */
    @Query("SELECT * FROM DiaryEntry")
    LiveData<List<DiaryEntry>> getAllDiaryEntries();

    /**
     * Gets all diary entries and returns in form of List
     * @return in form of list
     */
    @Query("SELECT * FROM DiaryEntry")
    List<DiaryEntry> getAllEntriesList();

    /**
     * Get Diary entry where diemId = an integer
     * @param id
     * @return List of entries
     */
    @Query("SELECT * FROM DiaryEntry where diemId = :id")
    List<DiaryEntry> getAllEntriesListByDiemId(int id);

    /**
     * Get Diary entry where diemId = an integer
     * @param id
     * @return List of entries
     */
    @Query("Select * from diaryentry where diemId = :id")
    LiveData<List<DiaryEntry>> getEntriesByDiemId(int id);
}
