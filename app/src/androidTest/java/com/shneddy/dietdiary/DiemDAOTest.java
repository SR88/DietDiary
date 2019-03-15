package com.shneddy.dietdiary;

import android.util.Log;

import com.shneddy.dietdiary.dao.DiemDAO;
import com.shneddy.dietdiary.dao.FoodTypeDAO;
import com.shneddy.dietdiary.database.FoodDiaryDatabase;
import com.shneddy.dietdiary.entity.Diem;
import com.shneddy.dietdiary.entity.Diem;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;

@SuppressWarnings({"unchecked"})
@RunWith(JUnit4.class)
public class DiemDAOTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();
    private FoodDiaryDatabase database;
    private DiemDAO dao;
    private Observer<List<Diem>> observer;
    @Before
    public void setUp() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(),
                FoodDiaryDatabase.class).build();

        dao = database.diemDAO();
    }
    @Test
    public void insertDiem(){
        dao.deleteAllDiem();

        observer = new Observer<List<Diem>>() {
            @Override
            public void onChanged(List<Diem> diems) {
            }
        };

        Diem testDiem = new Diem("Dec 1 2019");
        testDiem.setId(1);
        dao.insert(testDiem);

        // Verify # of Entries in table
        Assert.assertEquals(testDiem.getDate(), dao.getByDateString("Dec 1 2019").get(0).getDate());
    }
    @Test
    public void updateDiemType(){
        Diem testDiem = new Diem("Dec 1 2019");
        testDiem.setId(1);

        dao.insert(testDiem);

        Assert.assertEquals("Dec 1 2019", dao.getByDateString("Dec 1 2019").get(0).getDate()
        ); // Assert that our original description is correct

        Diem updateDiem = dao.getByDateString("Dec 1 2019").get(0);
        updateDiem.setDate("Nov 1 2019");
        dao.update(updateDiem);

        Assert.assertEquals("Nov 1 2019", dao.getByDateString("Nov 1 2019").get(0).
                getDate());  // assert that our updated description is correct
    }
    @Test
    public void deleteDiem(){
        Diem testFoodType = new Diem("Dec 1 2019");
        dao.insert(testFoodType);
        Assert.assertEquals(1, dao.getByDateString("Dec 1 2019")
                .size()); // assert that our table size is 1 rows big

        Diem foodTypeToDelete = dao.getByDateString("Dec 1 2019").get(0);

        dao.delete(foodTypeToDelete);

        Assert.assertEquals(0, dao.getByDateString("Dec 1 2019")
                .size()); // assert that our table is now 0 rows big
    }
    @After
    public void tearDown(){
        database.close();
    }

}
