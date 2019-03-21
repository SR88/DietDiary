package com.shneddy.dietdiary.activity;

import android.content.DialogInterface;
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
import com.shneddy.dietdiary.viewmodel.OperationsViewModel;
import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.viewmodel.TypeAndFoodViewModel;
import com.shneddy.dietdiary.adapters.FoodTypeAdapter;
import com.shneddy.dietdiary.entity.FoodType;
import com.shneddy.dietdiary.entity.TypeAndFood;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
/**
 * Created By Seth Sneddon Mar 2019
 */
public class AllFoodTypes extends AppCompatActivity {

    public static final String FOODTYPE_ID = "package com.shneddy.dietdiary.activity.EXTRA_FOODTYPE_ID";
    public static final String FOODTYPE_NAME = "package com.shneddy.dietdiary.activity.EXTRA_FOODTYPE_NAME";
    public static final String FOODTYPE_DESCRIPTION = "package com.shneddy.dietdiary.activity.EXTRA_FOODTYPE_DESCRIPTION";
    public static final int ADD_FOODTYPE_REQUEST = 1;
    public static final int EDIT_FOODTYPE_REQUEST = 2;
    private OperationsViewModel operationsViewModel;
    private TypeAndFoodViewModel joinVM;

    /**
     * When the screen/activity starts, this method is automatically executed.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_foodtypes);

        setTitle("Food Types/Categories");

        /*
            Start activity to create new food type.
         */
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


        joinVM = ViewModelProviders.of(this).get(TypeAndFoodViewModel.class);

        /*
            Setup list on screen with data from db.
         */
        operationsViewModel = ViewModelProviders.of(this).get(OperationsViewModel.class);
        operationsViewModel.getAllFoodTypes().observe(this, new Observer<List<FoodType>>() {
            @Override
            public void onChanged(List<FoodType> foodTypes) {
                foodTypeAdapter.setFoodTypes(foodTypes);
            }
        });



        /*
            The following implemented methods enable all of the UI interactions to swipe to delete
            or edit an item
         */
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            // Swipe to edit or delete depending on the direction
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {

                    FoodType tempFood = foodTypeAdapter.getFoodTypeAt(viewHolder
                            .getAdapterPosition());
                    List<TypeAndFood> checkList = joinVM.getByIdList(tempFood.getId());

                    if(checkList.get(0).relFoodList.size() > 0){
                        // todo create popup and cascade delete if true
                        AlertDialog.Builder alertBuilder = new AlertDialog
                                .Builder(AllFoodTypes.this);
                        alertBuilder.setMessage("There are Foods attached to this Food Type. " +
                                "If you choose to delete this Food Type, the Foods associated " +
                                "with this type and their entries in your diary will also be" +
                                " deleted!")
                                .setCancelable(true)
                                .setPositiveButton("Delete Food Type",
                                        new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        deleteFoodType(checkList.get(0).foodType.getId());
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface
                                        .OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        foodTypeAdapter.notifyItemChanged(viewHolder
                                                .getAdapterPosition());
                                    }
                                });
                        AlertDialog alertDialog = alertBuilder.create();
                        alertDialog.setTitle("Attached Foods Detected!");
                        alertDialog.show();
                    } else {
                        deleteFoodType(checkList.get(0).foodType.getId());
                    }
                }


                if (direction == ItemTouchHelper.RIGHT) {
                    FoodType editedFood = foodTypeAdapter
                            .getFoodTypeAt(viewHolder.getAdapterPosition());
                    Intent intent = new Intent(AllFoodTypes.this, EditorFoodType.class);
                    intent.putExtra(FOODTYPE_ID, editedFood.getId());
                    intent.putExtra(FOODTYPE_NAME, editedFood.getType());
                    intent.putExtra(FOODTYPE_DESCRIPTION, editedFood.getDescription());
                    startActivityForResult(intent, EDIT_FOODTYPE_REQUEST);
                }

            }

            // Sets up the draw over on each item
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

            /*
                Sets the colors to change in the background of the item that is being swiped
                based on direction.
             */
            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {
                final View foregroundView = ((FoodTypeAdapter.FoodTypeHolder) viewHolder)
                        .viewForeground;

                View backgroundView = ((FoodTypeAdapter.FoodTypeHolder) viewHolder)
                        .viewBackground;

                TextView textViewDelete = recyclerView.findViewHolderForAdapterPosition(viewHolder
                        .getAdapterPosition()).itemView.findViewById(R.id.textview_delete_consumption);
                TextView textViewEdit = recyclerView.findViewHolderForAdapterPosition(viewHolder
                        .getAdapterPosition()).itemView.findViewById(R.id.textview_edit_consumption);
                ImageView iconDelete = recyclerView.findViewHolderForAdapterPosition(viewHolder
                        .getAdapterPosition()).itemView.findViewById(R.id.icon_delete_consumption);
                ImageView iconEdit = recyclerView.findViewHolderForAdapterPosition(viewHolder
                        .getAdapterPosition()).itemView.findViewById(R.id.icon_edit_consumption);

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

    /**
     * Deletes a foodtype in the database with the id of a particular integer value
     * @param id integer value of the pk to use to complete the transaction
     */
    private void deleteFoodType(int id) {
        FoodType deleteFoodType = new FoodType("dummy data", "dummy data");
        deleteFoodType.setId(id); // the only thing that room cares about when deleting
        operationsViewModel.deleteFoodType(deleteFoodType);
        Toast.makeText(AllFoodTypes.this, "Food Type was deleted.",
                Toast.LENGTH_SHORT).show();
    }

    /**
     * This method inserts or updates a food type based on the parameters given from the activity
     * results
     * @param requestCode whether it was an insert or edit command
     * @param resultCode if the activity was successful
     * @param data data associated with the edited or created food
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // if it was a request to create a new food
        if (requestCode == ADD_FOODTYPE_REQUEST && resultCode == RESULT_OK) {
            String foodTypeName = data.getStringExtra(EditorFoodType.EXTRA_FOODTYPE);
            String foodTypeDescription = data.getStringExtra(EditorFoodType.EXTRA_FOODTYPE_DESCRIPTION);

            FoodType foodType = new FoodType(foodTypeName, foodTypeDescription);
            operationsViewModel.insertFoodType(foodType);

            Toast.makeText(this, "Food Type saved.", Toast.LENGTH_SHORT).show();
        }
        // if it was a request to edit a food
        if (requestCode == EDIT_FOODTYPE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(EditorFoodType.EXTRA_FOODTYPE_ID, -1);

            if (id != -1) {
                String updateFoodTypeName = data.getStringExtra(EditorFoodType.EXTRA_FOODTYPE);
                String updateFoodTypeDescription = data.getStringExtra(EditorFoodType
                        .EXTRA_FOODTYPE_DESCRIPTION);

                FoodType updateFood = new FoodType(updateFoodTypeName, updateFoodTypeDescription);
                updateFood.setId(id);
                operationsViewModel.updateFoodType(updateFood);
            }
        }
    }
}
