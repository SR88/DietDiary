package com.shneddy.dietdiary.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shneddy.dietdiary.R;

public class MainMenu extends AppCompatActivity {

    private Button logs, foods, foodTypes;
    TextView sugarConsume, subtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        setupGUI();

        foods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, AllFoods.class);
                startActivity(intent);
            }
        });

        foodTypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, AllFoodTypes.class);
                startActivity(intent);
            }
        });

        logs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, AllEntries.class);
                startActivity(intent);
            }
        });

    }

    private void setupGUI() {
        this.logs = findViewById(R.id.button_diary);
        this.foods = findViewById(R.id.button_foods);
        this.foodTypes = findViewById(R.id.button_foodtypes);
        this.sugarConsume = findViewById(R.id.textview_main_sugar);
        this.subtext = findViewById(R.id.textview_main_subtext);

        subtext.setText("Grams sugar consumed\ntoday");
    }
}
