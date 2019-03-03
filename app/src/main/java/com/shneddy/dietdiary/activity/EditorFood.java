package com.shneddy.dietdiary.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.ViewModel;
import com.shneddy.dietdiary.adapters.FoodTypeSpinnerAdapter;
import com.shneddy.dietdiary.entity.FoodType;

import java.util.ArrayList;
import java.util.List;

public class EditorFood extends AppCompatActivity {

    private List<FoodType> foodTypeList = new ArrayList<>();
    private List<FoodType> intermediateList = new ArrayList();
    ViewModel vmFoodType;

    BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return foodTypeList.size();
        }

        @Override
        public Object getItem(int position) {
            return foodTypeList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return foodTypeList.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            FoodTypeSpinnerHolder foodTypeSpinnerHolder;
            View foodTypeView = convertView;

            if (foodTypeView == null){
                foodTypeView = getLayoutInflater().inflate(R.layout.spinner_foodtype_row, parent, false);

                foodTypeSpinnerHolder = new FoodTypeSpinnerHolder();
                foodTypeSpinnerHolder.foodType = foodTypeView.findViewById(R.id.textView_spinner_foodtype);
                foodTypeView.setTag(foodTypeSpinnerHolder);
            } else {
                foodTypeSpinnerHolder = (FoodTypeSpinnerHolder) foodTypeView.getTag();
            }

            FoodType foodType = foodTypeList.get(position);
            foodTypeSpinnerHolder.foodType.setText(foodType.getType());


            return foodTypeView;
        }

        class FoodTypeSpinnerHolder {
            private TextView foodType;
            private TextView foodId;

        }


    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_food);



        Spinner spinner = findViewById(R.id.spinner_foodtype);

        vmFoodType = ViewModelProviders.of(this).get(ViewModel.class);
//        vmFoodType.getAllFoodTypes().observe(this, new Observer<List<FoodType>>() {
//            @Override
//            public void onChanged(List<FoodType> foodTypes) {
////                for (FoodType f : foodTypes){
////                    FoodType temp = f;
////                    intermediateList.add(temp);
////                }
//            }
//        });

        foodTypeList = vmFoodType.getAllFoodTypesList();

//        for (FoodType f : foodTypeList){
            Log.d("Editor: ", String.valueOf(foodTypeList.size()));
//        }


        spinner.setAdapter(baseAdapter);




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                baseAdapter.notifyDataSetChanged();


                Toast.makeText(EditorFood.this, foodTypeList.get(position).toString(), Toast.LENGTH_SHORT).show();

                Log.d("Editor Food: ", foodTypeList.get(position).toString());
                Log.d("Editor Food", "failure");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("Editor Food: ", "NOThING");
            }
        });

//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                String item = foodTypeArrayList.get(position).getType();
//
//                Toast.makeText(EditorFood.this, "selected item" + item, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


    }
}
