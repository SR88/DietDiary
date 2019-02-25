package com.shneddy.dietdiary.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.ViewModel;
import com.shneddy.dietdiary.adapters.FoodTypeAdapter;
import com.shneddy.dietdiary.entity.FoodType;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.widget.Toast.LENGTH_SHORT;

public class Overview extends AppCompatActivity {

    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_overview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final FoodTypeAdapter foodTypeAdapter = new FoodTypeAdapter();
        recyclerView.setAdapter(foodTypeAdapter);

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getAllFoodTypes().observe(this, new Observer<List<FoodType>>() {
            @Override
            public void onChanged(List<FoodType> foodTypes) {
                // update recyclerview
                Toast.makeText(Overview.this, "onChanged", Toast.LENGTH_SHORT).show();
                foodTypeAdapter.setFoodTypes(foodTypes);
            }
        });


    }
}
