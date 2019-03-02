package com.shneddy.dietdiary.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.ViewModel;
import com.shneddy.dietdiary.adapters.ComplexFoodAndTypeAdapter;
import com.shneddy.dietdiary.entity.FoodAndTypeData;

import java.util.List;

public class AllFoods extends AppCompatActivity {


    public static final int ADD_FOOD_REQUEST = 1;
    public static final int EDIT_FOOD_REQUEST = 2;
    private ViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_foods);

        setTitle("All Of Your Foods");


        RecyclerView recyclerView = findViewById(R.id.recyclerview_allfoods);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ComplexFoodAndTypeAdapter complexFoodAndTypeAdapter = new ComplexFoodAndTypeAdapter();
        recyclerView.setAdapter(complexFoodAndTypeAdapter);

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getAllFoodsAndTypes().observe(this, new Observer<List<FoodAndTypeData>>() {
            @Override
            public void onChanged(List<FoodAndTypeData> foodAndTypeData) {
                complexFoodAndTypeAdapter.setFoodAndTypes(foodAndTypeData);
            }
        });


        // Floating action button to add new food
        // FloatingActionButton fab = findViewById(R.id.fab_add_food);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AllFoods.this, EditorFood.class);
//                startActivityForResult(intent, ADD_FOOD_REQUEST);
//
//    }
    }


}
