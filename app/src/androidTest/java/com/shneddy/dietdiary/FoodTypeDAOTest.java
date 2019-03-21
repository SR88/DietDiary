package com.shneddy.dietdiary;

import android.util.Log;
import com.shneddy.dietdiary.dao.FoodTypeDAO;
import com.shneddy.dietdiary.database.FoodDiaryDatabase;
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
import java.util.List;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
/**
 * Created By Seth Sneddon Feb 2019
 */
/**
 *  This test class is the first class in our QA regimen.  All further test classes build on
 *  the fact that these tests verify that FoodTypeDAO works and that the database is responsive,
 *  accurate, and precise.
 */
@SuppressWarnings({"unchecked"})
@RunWith(JUnit4.class)
public class FoodTypeDAOTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();
    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    private FoodDiaryDatabase database;
    private FoodTypeDAO foodTypeDAO;
    private Observer<List<FoodType>> observer;

    @Before
    public void setUp() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(),
                FoodDiaryDatabase.class).build();

        foodTypeDAO = database.foodTypeDAO();
    }

    // This test is to ensure that insertion of a new row into the foodtype table is successful
    @Test
    public void insertFoodType(){
        foodTypeDAO.deleteAllFoodTypes(); // make sure that we have no entries in the table

        observer = new Observer<List<FoodType>>() {
            @Override
            public void onChanged(List<FoodType> foodTypes) {
            }
        };

        FoodType testFoodType = new FoodType("type", "insertFoodTypeTestDescrip");

        foodTypeDAO.getAllFoodTypes().observeForever(observer);
        foodTypeDAO.insert(testFoodType);

        // Verify # of Entries in table
        Assert.assertEquals(1, foodTypeDAO.getAllFoodTypesList().size());
        // Verify ID number is 1 as there is only one foodType that has been inserted
        Assert.assertEquals(1, foodTypeDAO.getAllFoodTypesList().get(0).getId());
    }

    // Testing insertion of a new row and testing of a updates to aforementioned row
    @Test
    public void updateFoodType(){
        foodTypeDAO.deleteAllFoodTypes(); // make sure that we have no entries in the table

        FoodType testFoodType = new FoodType("type", "updateFoodTypeTestDescrip");

        foodTypeDAO.insert(testFoodType);

        Assert.assertEquals("updateFoodTypeTestDescrip", foodTypeDAO.getAllFoodTypesList()
                .get(0).getDescription()); // Assert that our original description is correct

        FoodType updatedFoodType = foodTypeDAO.getAllFoodTypesList().get(0);
        updatedFoodType.setDescription("updatedDescriptionComplete");
        foodTypeDAO.update(updatedFoodType);

        Assert.assertEquals("updatedDescriptionComplete", foodTypeDAO.getAllFoodTypesList()
                .get(0).getDescription());  // assert that our updated description is correct
    }

    // Testing insertion of a new row and testing of a deletion aforementioned row
    @Test
    public void deleteFoodType(){
        foodTypeDAO.deleteAllFoodTypes(); // make sure that we have no entries in the table

        FoodType testFoodType = new FoodType("type", "deleteFoodTypeTestDescrip");
        foodTypeDAO.insert(testFoodType);
        Assert.assertEquals(1, foodTypeDAO.getAllFoodTypesList()
                .size()); // assert that our table size is 1 rows in size

        FoodType foodTypeToDelete = foodTypeDAO.getAllFoodTypesList().get(0);

        foodTypeDAO.delete(foodTypeToDelete);

        Assert.assertEquals(0, foodTypeDAO.getAllFoodTypesList()
                .size()); // assert that our table is now 0 rows in size
    }

    @After
    public void tearDown(){
        database.close();
    }


}
