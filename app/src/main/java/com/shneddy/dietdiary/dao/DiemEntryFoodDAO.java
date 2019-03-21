package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.Diem;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;
/**
 * Created By Seth Sneddon Feb 2019
 */
@Dao
public interface DiemEntryFoodDAO {

    /**
     * join on table where diary entry has a particular diem id and that table also has food fkeys
     * @param id
     * @return compatible result list
     */
    @Query("select * from diem inner join diaryentry on diaryentry.diemId = diem.id inner join food on diaryentry.foodId = food.id where diemId = :id")
    List<Diem> getByDiemId(int id);

}
