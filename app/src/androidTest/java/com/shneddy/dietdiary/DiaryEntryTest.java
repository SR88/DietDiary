package com.shneddy.dietdiary;

import android.util.Log;

import com.shneddy.dietdiary.dao.DiaryEntryDAO;
import com.shneddy.dietdiary.dao.FoodDAO;
import com.shneddy.dietdiary.dao.FoodTypeDAO;
import com.shneddy.dietdiary.database.FoodDiaryDatabase;
import com.shneddy.dietdiary.entity.DiaryEntry;
import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.entity.FoodType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Date;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;

@SuppressWarnings({"unchecked"})
@RunWith(JUnit4.class)
public class DiaryEntryTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    private FoodDiaryDatabase database;
    private FoodDAO foodDAO;
    private FoodTypeDAO foodTypeDAO;
    private DiaryEntryDAO entryDAO;

    @Before
    public void setUp() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(),
                FoodDiaryDatabase.class).build();

        foodDAO = database.foodDAO();
        foodTypeDAO = database.foodTypeDAO();
        entryDAO = database.entryDAO();
    }

    @Test
    public void insert(){
        // inserting food & type first as it is needed as a fk
        FoodType testFoodType = new FoodType("type", "insertFoodTypeTestDescrip");
        foodTypeDAO.insert(testFoodType);

        Food food = new Food("Cupcake",25.9,
                foodTypeDAO.getAllFoodTypesList().get(0).getId());
        foodDAO.insert(food);

        Assert.assertEquals(0, entryDAO.getAllEntriesList().size()); // make sure our table is empty

        Date date = new Date();
        Log.d("Diary Entry Test", date.toString() + " current date");
        DiaryEntry entry = new DiaryEntry(
                foodDAO.getAllFoodsList().get(0).getId(),
                .5,
                date.toString()
        );

        entryDAO.insert(entry);

        Assert.assertEquals(1, entryDAO.getAllEntriesList().size()); // make sure table has 1 row

        Assert.assertEquals(1, entryDAO.getAllEntriesList().get(0).getId()); // verify right pk
        Assert.assertEquals(1, entryDAO.getAllEntriesList().get(0).getFoodId()); // verify right fk for food
        Assert.assertTrue(date.toString().equals(entryDAO.getAllEntriesList().get(0)
                .getDate())); // verify right date
        Assert.assertTrue(.5 == entryDAO.getAllEntriesList().get(0).getPortionSize()); // verify right fk for food
    }


    @After
    public void tearDown(){
        database.close();
    }
}
