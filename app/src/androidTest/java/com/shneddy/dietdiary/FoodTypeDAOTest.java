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

    @Test
    public void insertFoodType(){
        foodTypeDAO.deleteAllFoodTypes(); // make sure that we have no entries in the table

        observer = new Observer<List<FoodType>>() {
            @Override
            public void onChanged(List<FoodType> foodTypes) {
                Log.d("Insert Test", "observer has noticed");
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

    @Test
    public void deleteFoodType(){
        foodTypeDAO.deleteAllFoodTypes(); // make sure that we have no entries in the table

        FoodType testFoodType = new FoodType("type", "deleteFoodTypeTestDescrip");
        foodTypeDAO.insert(testFoodType);
        Assert.assertEquals(1, foodTypeDAO.getAllFoodTypesList().size()); // assert that our table size is 1 rows big

        FoodType foodTypeToDelete = foodTypeDAO.getAllFoodTypesList().get(0);

        foodTypeDAO.delete(foodTypeToDelete);

        Assert.assertEquals(0, foodTypeDAO.getAllFoodTypesList().size()); // assert that our table is now 0 rows big
    }

    @After
    public void tearDown(){
        database.close();
    }


}
