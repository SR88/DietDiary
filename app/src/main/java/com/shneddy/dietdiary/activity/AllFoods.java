package com.shneddy.dietdiary.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.ViewModel;
import com.shneddy.dietdiary.adapters.ComplexFoodAndTypeAdapter;
import com.shneddy.dietdiary.adapters.FoodTypeAdapter;
import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.entity.FoodAndTypeData;
import com.shneddy.dietdiary.entity.FoodType;

import java.util.List;

public class AllFoods extends AppCompatActivity {

    public static final String FOOD_ID =
            "package com.shneddy.dietdiary.activity.ID";
    public static final String FOOD_NAME =
            "package com.shneddy.dietdiary.activity.NAME";
    public static final String FOOD_SUGAR =
            "package com.shneddy.dietdiary.activity.SUGAR";
    public static final String FOOD_FOODTYPE =
            "package com.shneddy.dietdiary.activity.FOODTYPE_ID";
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
        FloatingActionButton fab = findViewById(R.id.fab_add_food);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllFoods.this, EditorFood.class);
                startActivityForResult(intent, ADD_FOOD_REQUEST);
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
                // Delete food
                if (direction == ItemTouchHelper.LEFT) {
                    FoodAndTypeData itemToDelete = complexFoodAndTypeAdapter
                            .getFoodAndTypeAt(viewHolder.getAdapterPosition());

                    Food foodToDelete = new Food("", 2.5, 1); // dummy data
                    foodToDelete.setId(itemToDelete.getId()); // the important part ROOM's @Delete annotation keys off

                    viewModel.deleteFood(foodToDelete);
                    Toast.makeText(AllFoods.this, "Food was deleted.",
                            Toast.LENGTH_SHORT).show();
                }

                // Edit a food
                if (direction == ItemTouchHelper.RIGHT) {
                    FoodAndTypeData editedFood = complexFoodAndTypeAdapter
                            .getFoodAndTypeAt(viewHolder.getAdapterPosition());
                    Intent intent = new Intent(AllFoods.this, EditorFood.class);
                    intent.putExtra(FOOD_ID, editedFood.getId());
                    startActivityForResult(intent, EDIT_FOOD_REQUEST);
                }

            }

            @Override
            public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                        RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                        int actionState, boolean isCurrentlyActive) {

                View foregroundView = ((ComplexFoodAndTypeAdapter.FoodAndTypeHolder) viewHolder).viewForeground;

                getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState,
                        isCurrentlyActive);
            }

            @Override
            public void clearView(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder) {
                final View foregroundView = ((ComplexFoodAndTypeAdapter.FoodAndTypeHolder) viewHolder)
                        .viewForeground;
                getDefaultUIUtil().clearView(foregroundView);
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {
                final View foregroundView = ((ComplexFoodAndTypeAdapter.FoodAndTypeHolder) viewHolder)
                        .viewForeground;

                View backgroundView = ((ComplexFoodAndTypeAdapter.FoodAndTypeHolder) viewHolder)
                        .viewBackground;

                TextView textViewDelete = recyclerView.findViewHolderForAdapterPosition(viewHolder.getAdapterPosition()).itemView.findViewById(R.id.textview_delete_food);
                TextView textViewEdit = recyclerView.findViewHolderForAdapterPosition(viewHolder.getAdapterPosition()).itemView.findViewById(R.id.textview_edit_food);
                ImageView iconDelete = recyclerView.findViewHolderForAdapterPosition(viewHolder.getAdapterPosition()).itemView.findViewById(R.id.icon_delete_food);
                ImageView iconEdit = recyclerView.findViewHolderForAdapterPosition(viewHolder.getAdapterPosition()).itemView.findViewById(R.id.icon_edit_food);

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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // if new food created
//        if (requestCode == ADD_FOOD_REQUEST && resultCode == RESULT_OK){
//            String foodName = data.getStringExtra(EditorFood.EXTRA_FOOD_FOOD_NAME);
//            double gramsSugar = data.getDoubleExtra(EditorFood.EXTRA_FOOD_SUGARS, 5.5);
//            int foodType = data.getIntExtra(EditorFood.EXTRA_FOOD_FOODTYPE_ID,-1);
//
//            Food newFood = new Food(foodName, gramsSugar, foodType);
//            viewModel.insertFood(newFood);
//        }
//
//        // if edit complete
//        if (requestCode == EDIT_FOOD_REQUEST && resultCode == RESULT_OK){
//            int id = data.getIntExtra(EditorFood.EXTRA_FOOD_ID,-1);
//
//            if (id != -1) {
//                String foodName = data.getStringExtra(EditorFood.EXTRA_FOOD_FOOD_NAME);
//                double gramsSugar = data.getDoubleExtra(EditorFood.EXTRA_FOOD_SUGARS, 5.5);
//                int foodType = data.getIntExtra(EditorFood.EXTRA_FOOD_FOODTYPE_ID,-1);
//
//                Food updateFood = new Food(foodName, gramsSugar, foodType);
//                updateFood.setId(id);
//                viewModel.updateFood(updateFood);
//            }
//        }
//    }
}
