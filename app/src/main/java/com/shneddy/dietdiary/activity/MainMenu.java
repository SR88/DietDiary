package com.shneddy.dietdiary.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.entity.DiaryEntry;
import com.shneddy.dietdiary.entity.Diem;
import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.intermediates.EntryAndFoodData;
import com.shneddy.dietdiary.viewmodel.OperationsViewModel;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Created By Seth Sneddon Mar 2019
 */
public class MainMenu extends AppCompatActivity {


    private Button logs, foods, foodTypes, search;
    private TextView sugarConsume, subtext;
    private OperationsViewModel opsVM;
    private String dateQuery;

    /**
     * When the screen/activity starts, this method is automatically executed.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        setupGUI();

        DateFormat df = new SimpleDateFormat("yyyy MMM dd");
        Date date = new Date();
        dateQuery = df.format(date);

        opsVM = ViewModelProviders.of(this).get(OperationsViewModel.class);


        // go to all foods list
        foods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, AllFoods.class);
                startActivity(intent);
            }
        });

        // go to all food types list
        foodTypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, AllFoodTypes.class);
                startActivity(intent);
            }
        });

        // go to all food diary dates
        logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, AllEntries.class);
                startActivity(intent);
            }
        });

        // go to search activity
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, SearchActivity.class);
                startActivity(intent);

            }
        });

        calculateSugarsToday();

    }

    /**
     * General method that setups the GUI on this screen
     */
    private void setupGUI() {
        this.logs = findViewById(R.id.button_diary);
        this.foods = findViewById(R.id.button_foods);
        this.foodTypes = findViewById(R.id.button_foodtypes);
        this.sugarConsume = findViewById(R.id.textview_main_sugar);
        this.subtext = findViewById(R.id.textview_main_subtext);
        this.search = findViewById(R.id.button_menu_search);

        subtext.setText("Grams sugar consumed\ntoday");
    }

    /**
     * Calculates sugars for today's date if applicable record exists in the database.
     * If record does not exist, it displays 0 foods tracked
     */
    private void calculateSugarsToday(){
        List<EntryAndFoodData> intermediateList = new ArrayList<>();
        List<Diem> list = opsVM.getDiemByDate(dateQuery);
        if(list.size() > 0){
            List<DiaryEntry> entries = opsVM.getListEntriesByDiemId(list.get(0).getId());
            if(entries.size() > 0){
                for (DiaryEntry d : entries) {
                    EntryAndFoodData collectiveData = new EntryAndFoodData();
                    int foodId = d.getFoodId();
                    Food tempFood = opsVM.getFoodById(foodId);
                    collectiveData.setCalcSugars(d.getPortionSize() * tempFood.getGramsSugar());
                    intermediateList.add(collectiveData);
                }
            }

            if(intermediateList.size() > 0){
                double sugars = 0.0;
                for(EntryAndFoodData data : intermediateList){
                    sugars += data.getCalcSugars();
                }
                DecimalFormat decimalFormat = new DecimalFormat("####.#");
                sugarConsume.setText(String.valueOf(decimalFormat.format(sugars)));
                subtext.setText("grams sugar consumed in\n" + intermediateList.size() + " foods today.");

            }

        } else {
            sugarConsume.setText("0");
            subtext.setText("Foods were tracked today.");
        }
    }

    /**
     * Checks to make sure that today's sugar that is displayed on the main menu is up to date
     */
    @Override
    protected void onResume() {
        super.onResume();
        calculateSugarsToday();
    }
}

