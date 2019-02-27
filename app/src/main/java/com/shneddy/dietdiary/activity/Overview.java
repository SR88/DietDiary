package com.shneddy.dietdiary.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.ViewModel;
import com.shneddy.dietdiary.adapters.FoodTypeAdapter;
import com.shneddy.dietdiary.entity.FoodType;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.widget.Toast.LENGTH_SHORT;

public class Overview extends AppCompatActivity {

    public static final int ADD_FOODTYPE_REQUEST = 1;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Overview.this, EditorFoodType.class);
                startActivityForResult(intent, ADD_FOODTYPE_REQUEST);
            }
        });

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
//                Toast.makeText(Overview.this, "onChanged", Toast.LENGTH_SHORT).show();
                foodTypeAdapter.setFoodTypes(foodTypes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deleteFoodType(foodTypeAdapter
                        .getFoodTypeAt(viewHolder.getAdapterPosition()));
                Toast.makeText(Overview.this, "Food Type was deleted.",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                        RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                        int actionState, boolean isCurrentlyActive) {

                View foregroundView = ((FoodTypeAdapter.FoodTypeHolder) viewHolder).viewForeground;

                getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState,
                        isCurrentlyActive);
            }

            @Override
            public void clearView(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder) {
                final View foregroundView = ((FoodTypeAdapter.FoodTypeHolder) viewHolder)
                        .viewForeground;
                getDefaultUIUtil().clearView(foregroundView);
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {
                final View foregroundView = ((FoodTypeAdapter.FoodTypeHolder) viewHolder)
                        .viewForeground;

                getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState,
                        isCurrentlyActive);
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_FOODTYPE_REQUEST && resultCode == RESULT_OK) {
            String foodTypeName = data.getStringExtra(EditorFoodType.EXTRA_FOODTYPE);
            String foodTypeDescription = data.getStringExtra(EditorFoodType.EXTRA_FOODTYPE_DESCRIPTION);

            FoodType foodType = new FoodType(foodTypeName, foodTypeDescription);
            viewModel.insertFoodType(foodType);

            Toast.makeText(this, "Food Type saved.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Food Type canceled.", Toast.LENGTH_SHORT).show();
        }

    }
}
