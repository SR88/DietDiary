package com.shneddy.dietdiary.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.adapters.FoodAdapter;
import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.viewmodel.SearchViewModel;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchActivity extends AppCompatActivity {

    private SearchViewModel sVM;
    private Button searchButton;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editText = findViewById(R.id.editText_search);
        searchButton = findViewById(R.id.button_search);
        RecyclerView rView = findViewById(R.id.recyclerview_searchresults);
        rView.setLayoutManager(new LinearLayoutManager(this));
        rView.setHasFixedSize(false);

        final FoodAdapter adapter = new FoodAdapter();
        rView.setAdapter(adapter);

        sVM = ViewModelProviders.of(this).get(SearchViewModel.class);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().trim().length() > 0) {
                    Log.d("SEARCH", editText.getText().toString());
                    sVM.setQuery(editText.getText().toString().trim());
                }
            }
        });
        sVM.results.observe(this, new Observer<List<Food>>() {
            @Override
            public void onChanged(List<Food> foods) {
                adapter.setFoods(foods);
            }
        });
    }



}
