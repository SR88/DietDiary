package com.shneddy.dietdiary.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.adapters.FoodAdapter;
import com.shneddy.dietdiary.adapters.TypeAndFoodJoinAdapter;
import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.entity.FoodAndEntry;
import com.shneddy.dietdiary.intermediates.FoodAndTypeData;
import com.shneddy.dietdiary.viewmodel.OperationsViewModel;
import com.shneddy.dietdiary.viewmodel.SearchViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SearchActivity extends AppCompatActivity {


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
    private OperationsViewModel opsVM;
    private Button searchButton;
    private EditText editText;
    private RecyclerView rView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setTitle("Is it in your Food List?");

        editText = findViewById(R.id.editText_search);
        searchButton = findViewById(R.id.button_search);
        rView = findViewById(R.id.recyclerview_searchresults);
        rView.setLayoutManager(new LinearLayoutManager(this));
        rView.setHasFixedSize(false);

        final FoodAdapter adapter = new FoodAdapter();
        rView.setAdapter(adapter);

        opsVM = ViewModelProviders.of(this).get(OperationsViewModel.class);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().length() > 0) {

                    List<Food> list = opsVM.searchForFoodsByString("%" + editText.getText().toString().toLowerCase() + "%");

                    adapter.setFoods(list);

                    if(list.size() < 1){
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(SearchActivity.this);
                        alertBuilder.setMessage("There is no food like this in your Food List.\n\nYou can always create it though!")
                                .setCancelable(false)
                                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        AlertDialog alertDialog = alertBuilder.create();
                        alertDialog.setTitle("Hmm... This doesn't exist.");
                        alertDialog.show();
                    }
                }
            }
        });

    }


}
