package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.Diem;
import com.shneddy.dietdiary.entity.DiemAndEntry;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Transaction;
import androidx.room.Update;
/**
 * Created By Seth Sneddon Feb 2019
 */
@Dao
public interface DiemDAO {

    /**
     * insert a day into db
     * @param diem
     */
    @Insert
    void insert(Diem diem);

    /**
     * update a day in the database
     * @param diem
     */
    @Update
    void update(Diem diem);

    /**
     * delete a day in the database
     * @param diem
     */
    @Delete
    void delete(Diem diem);

    /**
     * delete all days from the database
     */
    @Query("DELETE FROM diem")
    void deleteAllDiem();

    /**
     * select a day in the database with the date of
     * @param date
     * @return return list of dates
     */
    @Query("Select * from diem where diem.date = :date")
    List<Diem> getByDateString(String date);

    /**
     * get day in the data base where the pkey = and id
     * @param id
     * @return list of entries and days
     */
    @Transaction
    @Query("Select * from diem where diem.id = :id")
    List<DiemAndEntry> joinGetById(int id);

    /**
     * select all from the day/diem table
     * @return all days in the diem table
     */
    @Query("Select * from diem")
    LiveData<List<Diem>> getLiveDataDiem();

    /**
     * select days on diem table where they can span across the diary entry table and food table
     * @return list of compatible rows
     */
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("Select * from diem join diaryentry on diaryentry.diemId=diem.id inner join food on diaryentry.foodId=food.id ")
    LiveData<List<Diem>> getJoinedTables();
}
