package com.shneddy.dietdiary.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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

public class AllFoodTypes extends AppCompatActivity {

    public static final String FOODTYPE_ID = "package com.shneddy.dietdiary.activity.EXTRA_FOODTYPE_ID";
    public static final String FOODTYPE_NAME = "package com.shneddy.dietdiary.activity.EXTRA_FOODTYPE_NAME";
    public static final String FOODTYPE_DESCRIPTION = "package com.shneddy.dietdiary.activity.EXTRA_FOODTYPE_DESCRIPTION";
    public static final int ADD_FOODTYPE_REQUEST = 1;
    public static final int EDIT_FOODTYPE_REQUEST = 2;
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_foodtypes);

        setTitle("Food Types/Categories");

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllFoodTypes.this, EditorFoodType.class);
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
                if (direction == ItemTouchHelper.LEFT) {
                    viewModel.deleteFoodType(foodTypeAdapter
                            .getFoodTypeAt(viewHolder.getAdapterPosition()));
                    Toast.makeText(AllFoodTypes.this, "Food Type was deleted.",
                            Toast.LENGTH_SHORT).show();
                }

                if (direction == ItemTouchHelper.RIGHT) {
                    FoodType editedFood = foodTypeAdapter
                            .getFoodTypeAt(viewHolder.getAdapterPosition());
                    Intent intent = new Intent(AllFoodTypes.this, EditorFoodType.class);
                    Log.d("All foods to edit ID", String.valueOf(editedFood.getId()));
                    intent.putExtra(FOODTYPE_ID, editedFood.getId());
                    intent.putExtra(FOODTYPE_NAME, editedFood.getType());
                    intent.putExtra(FOODTYPE_DESCRIPTION, editedFood.getDescription());
//                    startActivityForResult(intent, EDIT_FOODTYPE_REQUEST);
                }

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

                View backgroundView = ((FoodTypeAdapter.FoodTypeHolder) viewHolder)
                        .viewBackground;

                TextView textViewDelete = recyclerView.findViewHolderForAdapterPosition(viewHolder.getAdapterPosition()).itemView.findViewById(R.id.textview_delete);
                TextView textViewEdit = recyclerView.findViewHolderForAdapterPosition(viewHolder.getAdapterPosition()).itemView.findViewById(R.id.textview_edit);
                ImageView iconDelete = recyclerView.findViewHolderForAdapterPosition(viewHolder.getAdapterPosition()).itemView.findViewById(R.id.icon_delete);
                ImageView iconEdit = recyclerView.findViewHolderForAdapterPosition(viewHolder.getAdapterPosition()).itemView.findViewById(R.id.icon_edit);

                if (dX > 0) {
                    backgroundView.setBackgroundColor(Color.parseColor("#f7af42"));
                    iconDelete.setVisibility(View.INVISIBLE);
                    textViewDelete.setVisibility(View.INVISIBLE);
                    iconEdit.setVisibility(View.VISIBLE);
                    textViewEdit.setVisibility(View.VISIBLE);

                } else {
                    backgroundView.setBackgroundColor(Color.parseColor("#f74242"));
                    iconEdit.setVisibility(View.INVISIBLE);
                    textViewEdit.setVisibility(View.INVISIBLE);
                    iconDelete.setVisibility(View.VISIBLE);
                    textViewDelete.setVisibility(View.VISIBLE);
                }

                getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY, actionState,
                        isCurrentlyActive);
            }
        })
                .attachToRecyclerView(recyclerView);
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
        }

        if (requestCode == EDIT_FOODTYPE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(EditorFoodType.EXTRA_FOODTYPE_ID, -1);

            if (id != -1) {
                String updateFoodTypeName = data.getStringExtra(EditorFoodType.EXTRA_FOODTYPE);
                String updateFoodTypeDescription = data.getStringExtra(EditorFoodType
                        .EXTRA_FOODTYPE_DESCRIPTION);

                FoodType updateFood = new FoodType(updateFoodTypeName, updateFoodTypeDescription);
                updateFood.setId(id);
                viewModel.updateFoodType(updateFood);
            }
        }
    }
}
