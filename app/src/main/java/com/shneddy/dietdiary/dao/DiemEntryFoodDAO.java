package com.shneddy.dietdiary.dao;

import com.shneddy.dietdiary.entity.Diem;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface DiemEntryFoodDAO {

    @Query("select * from diem inner join diaryentry on diaryentry.diemId = diem.id inner join food on diaryentry.foodId = food.id where diemId = :id")
    List<Diem> getByDiemId(int id);

}
