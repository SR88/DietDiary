package com.shneddy.dietdiary;

import com.shneddy.dietdiary.dao.FoodDAO;
import com.shneddy.dietdiary.dao.FoodTypeDAO;
import com.shneddy.dietdiary.database.FoodDiaryDatabase;
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
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;


/**
 *  This test class builds on the FoodTypeDAOTest class.  This class further fulfills our QA needs.
 */
@SuppressWarnings({"unchecked"})
@RunWith(JUnit4.class)
public class FoodDAOTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    private FoodDiaryDatabase database;
    private FoodDAO foodDAO;
    private FoodTypeDAO foodTypeDAO;

    @Before
    public void setUp() {
        database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(),
                FoodDiaryDatabase.class).build();

        foodDAO = database.foodDAO();
        foodTypeDAO = database.foodTypeDAO();
    }


    /**
        We will insert both a new food and food type to make sure our foodDAO and foreign keys work
     */
    @Test
    public void insertFood(){
        // inserting food type first as it is needed as a fk
        FoodType testFoodType = new FoodType("type", "insertFoodTypeTestDescrip");
        foodTypeDAO.insert(testFoodType);

        // Verify # of Entries in table
        Assert.assertEquals(1, foodTypeDAO.getAllFoodTypesList().size());
        // Verify ID number is 1 as there is only one foodType that has been inserted
        Assert.assertEquals(1, foodTypeDAO.getAllFoodTypesList().get(0).getId());

        Assert.assertEquals(0, foodDAO.getAllFoodsList().size()); // table now has 0 entry

        Food food = new Food("Cupcake",25.9,
                foodTypeDAO.getAllFoodTypesList().get(0).getId());

        foodDAO.insert(food);
        double doubleVerification = 25.9;

        Assert.assertEquals(1, foodDAO.getAllFoodsList().size()); // food table size now equals one
        Assert.assertEquals("Cupcake", foodDAO.getAllFoodsList().get(0).getName()); // food name is cupcake
        Assert.assertTrue(doubleVerification == foodDAO.getAllFoodsList().get(0)
                .getGramsSugar()); // food sugar grams is 25.9
        Assert.assertEquals(1, foodDAO.getAllFoodsList().get(0).getFoodTypeId()); // is tied to correct food type
    }

    @Test
    public void updateFood(){
        // inserting food type first as it is needed as a fk
        FoodType testFoodType = new FoodType("type", "FoodTypeTestDescrip");
        foodTypeDAO.insert(testFoodType);

        // Verify # of Entries in table
        Assert.assertEquals(1, foodTypeDAO.getAllFoodTypesList().size());
        // Verify ID number is 1 as there is only one foodType that has been inserted
        Assert.assertEquals(1, foodTypeDAO.getAllFoodTypesList().get(0).getId());

        Assert.assertEquals(0, foodDAO.getAllFoodsList().size()); // table now has 0 entry

        Food food = new Food("Cupcake",25.9,
                foodTypeDAO.getAllFoodTypesList().get(0).getId());
        foodDAO.insert(food);

        Food updatedFood = foodDAO.getAllFoodsList().get(0);
        updatedFood.setGramsSugar(0.1);
        updatedFood.setName("Lima Beans");
        foodDAO.update(updatedFood);

        double doubleVerification = 0.1;

        Assert.assertEquals(1, foodDAO.getAllFoodsList().size()); // food table size now equals one
        Assert.assertEquals("Lima Beans", foodDAO.getAllFoodsList().get(0).getName()); // food name is cupcake
        Assert.assertTrue(doubleVerification == foodDAO.getAllFoodsList().get(0)
                .getGramsSugar()); // food sugar grams is 0.1
    }

    @Test
    public void deleteFood(){
        // inserting food type first as it is needed as a fk
        FoodType testFoodType = new FoodType("type", "FoodTypeTestDescrip");
        foodTypeDAO.insert(testFoodType);

        Assert.assertEquals(0, foodDAO.getAllFoodsList().size()); // table now has 0 entry

        Food food = new Food("Cupcake",25.9,
                foodTypeDAO.getAllFoodTypesList().get(0).getId());
        foodDAO.insert(food);

        Assert.assertEquals(1, foodDAO.getAllFoodsList().size()); // table now has 1 entry

        Food deleteFood = foodDAO.getAllFoodsList().get(0);
        foodDAO.delete(deleteFood);

        Assert.assertEquals(0, foodDAO.getAllFoodsList().size()); // table now has 0 entry
    }

    @After
    public void tearDown(){
        database.close();
    }
}
