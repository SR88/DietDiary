package com.shneddy.dietdiary.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.entity.DiaryEntry;
import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.viewmodel.OperationsViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.shneddy.dietdiary.activity.EntryDetail.DIEM_ID;

public class EditorConsumption extends AppCompatActivity {

    private int diemId;
    private OperationsViewModel opsVM;
    private List<Food> foodList = new ArrayList<>();
    private Spinner spinner;
    private EditText portionSize;
    private Intent intent;

    BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return foodList.size();
        }

        @Override
        public Object getItem(int position) {
            return foodList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return foodList.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            FoodSpinner foodSpinner;
            View foodView = convertView;

            if (foodView == null) {
                foodView = getLayoutInflater().inflate(R.layout.spinner_foodtype_row, parent, false);
                foodSpinner = new FoodSpinner();
                foodSpinner.textViewFood = foodView.findViewById(R.id.textView_spinner_foodtype);
                foodView.setTag(foodSpinner);

            } else {
                foodSpinner = (FoodSpinner) foodView.getTag();
            }

            Food food = foodList.get(position);
            foodSpinner.textViewFood.setText(food.getName());
            return foodView;
        }

        class FoodSpinner {
            private TextView textViewFood;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_consumption);

        setTitle("New eaten food");
        this.spinner = findViewById(R.id.spinner_food);
        this.portionSize = findViewById(R.id.editText_consumption_portion);

        intent = getIntent();
        diemId = intent.getIntExtra(DIEM_ID, -1);

        opsVM = ViewModelProviders.of(this).get(OperationsViewModel.class);
        foodList = opsVM.getAllFoodsList();

        spinner.setAdapter(baseAdapter);
        spinner.setOnItemSelectedListener(new AdapterView
                .OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                baseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_save:
                saveConsumption();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveConsumption() {
        String error = "";
        if(portionSize.getText().toString().length() < 1){
            error += "Please fill in the portion size consumed";
        }

        if(error.length() > 1){
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            return;
        } else {
            DiaryEntry diaryEntry = new DiaryEntry(
                    getSpinnerVal(),
                    Double.parseDouble(portionSize.getText().toString()),
                    diemId);

            Log.d("Editor Consumption ", diaryEntry.toString());

            opsVM.insertFoodDiary(diaryEntry);
            setResult(RESULT_OK);
            finish();
        }
    }

    private int getSpinnerVal(){
        Food food = (Food) spinner.getSelectedItem();
        return food.getId();
    }

}
