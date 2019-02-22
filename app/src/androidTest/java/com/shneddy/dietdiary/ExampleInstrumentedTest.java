package com.shneddy.dietdiary;

import android.content.Context;

import com.shneddy.dietdiary.database.FoodDiaryDatabase;
import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.repository.FoodRepository;
import com.shneddy.dietdiary.repository.FoodTypeRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    Context context;

    @Before
    public void setup(){
        context = InstrumentationRegistry.getContext();
    }


    @Test
    public void insert(){
        
    }

        @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.shneddy.dietdiary", appContext.getPackageName());
    }
}
