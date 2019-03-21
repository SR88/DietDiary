package com.shneddy.dietdiary;

import android.util.Log;

import com.shneddy.dietdiary.dao.DiaryEntryDAO;
import com.shneddy.dietdiary.dao.DiemDAO;
import com.shneddy.dietdiary.dao.FoodDAO;
import com.shneddy.dietdiary.dao.FoodTypeDAO;
import com.shneddy.dietdiary.database.FoodDiaryDatabase;
import com.shneddy.dietdiary.entity.DiaryEntry;
import com.shneddy.dietdiary.entity.Diem;
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
/**
 * Created By Seth Sneddon Feb 2019
 */

/**
 *  The final class in our QA regimen.  All DAO test classes before have verified that all DAOs
 *  work and that the database is fully functional.
 */
@SuppressWarnings({"unchecked"})
@RunWith(JUnit4.class)
public class DiaryEntryDAOTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    private FoodDiaryDatabase database;
    private FoodDAO foodDAO;
    private FoodTypeDAO foodTypeDAO;
    private DiaryEntryDAO entryDAO;
    private DiemDAO diemDAO;

    @Before
    public void setUp() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(),
                FoodDiaryDatabase.class).build();

        foodDAO = database.foodDAO();
        foodTypeDAO = database.foodTypeDAO();
        entryDAO = database.entryDAO();
        diemDAO = database.diemDAO();
    }

    @Test
    public void insert(){
        // inserting food & type first as it is needed as a fk
        FoodType testFoodType = new FoodType("type", "insertFoodTypeTestDescrip");
        foodTypeDAO.insert(testFoodType);

        Diem diem = new Diem("Dec 1 2019");
        diemDAO.insert(diem);

        Food food = new Food("Cupcake",25.9,
                foodTypeDAO.getAllFoodTypesList().get(0).getId());
        foodDAO.insert(food);

        Assert.assertEquals(0, entryDAO.getAllEntriesList().size()); // make sure our table is empty

        Date date = new Date();
        DiaryEntry entry = new DiaryEntry(
                foodDAO.getAllFoodsList().get(0).getId(),
                .5,
                1
        );

        entryDAO.insert(entry);

        Assert.assertEquals(1, entryDAO.getAllEntriesList().size()); // make sure table has 1 row

        Assert.assertEquals(1, entryDAO.getAllEntriesList().get(0).getId()); // verify right pk
        Assert.assertEquals(1, entryDAO.getAllEntriesList().get(0).getFoodId()); // verify right fk for food
        Assert.assertEquals(1, entryDAO.getAllEntriesList().get(0)
                .getDiemId()); // verify right date
        Assert.assertTrue(.5 == entryDAO.getAllEntriesList().get(0).getPortionSize()); // verify right fk for food
    }

    @Test
    public void update(){
        // inserting food & type first as it is needed as a fk
        FoodType testFoodType = new FoodType("type", "insertFoodTypeTestDescrip");
        foodTypeDAO.insert(testFoodType);

        Diem diem = new Diem("Dec 1 2019");
        diemDAO.insert(diem);

        Food food = new Food("Cupcake",25.9,
                foodTypeDAO.getAllFoodTypesList().get(0).getId());
        foodDAO.insert(food);

        Date date = new Date();
        DiaryEntry entry = new DiaryEntry(
                foodDAO.getAllFoodsList().get(0).getId(),
                .5,
                1
        );

        entryDAO.insert(entry);


        double newPortion = 1.5;

        DiaryEntry updatedEntry = entryDAO.getAllEntriesList().get(0);
        updatedEntry.setPortionSize(newPortion);

        entryDAO.update(updatedEntry);

        Assert.assertTrue(newPortion == entryDAO.getAllEntriesList().get(0)
                .getPortionSize()); // test to see if update worked on portion

    }

    @Test
    public void delete(){
        // inserting food & type first as it is needed as a fk
        FoodType testFoodType = new FoodType("type", "insertFoodTypeTestDescrip");
        foodTypeDAO.insert(testFoodType);

        Diem diem = new Diem("Dec 1 2019");
        diemDAO.insert(diem);

        Food food = new Food("Cupcake",25.9,
                foodTypeDAO.getAllFoodTypesList().get(0).getId());
        foodDAO.insert(food);

        Assert.assertEquals(0, entryDAO.getAllEntriesList().size()); // make sure our table is empty

        Date date = new Date();
        DiaryEntry entry = new DiaryEntry(
                foodDAO.getAllFoodsList().get(0).getId(),
                .5,
                1
        );

        entryDAO.insert(entry);

        Assert.assertEquals(1, entryDAO.getAllEntriesList().size()); // make sure table has 1 row

        DiaryEntry deletionCandidate = entryDAO.getAllEntriesList().get(0);

        entryDAO.delete(deletionCandidate);

        Assert.assertEquals(0, entryDAO.getAllEntriesList().size()); // test deletion, 0 rows in table

    }

    @After
    public void tearDown(){
        database.close();
    }
}
