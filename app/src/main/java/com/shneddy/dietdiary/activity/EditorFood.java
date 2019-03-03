package com.shneddy.dietdiary.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.ViewModel;
import com.shneddy.dietdiary.entity.FoodType;
import java.util.ArrayList;
import java.util.List;

public class EditorFood extends AppCompatActivity {

    public static final String EXTRA_FOOD_FOODTYPE_ID =
            "package com.shneddy.dietdiary.activity.FOODTYPE_ID";
    public static final String EXTRA_FOOD_FOOD_NAME =
            "package com.shneddy.dietdiary.activity.FOOD_NAME";
    public static final String EXTRA_FOOD_SUGARS =
            "package com.shneddy.dietdiary.activity.FOOD_SUGARS";
    public static final String  EXTRA_FOOD_ID =
            "package com.shneddy.dietdiary.activity.FOOD_ID";

    private int previousId;
    private List<FoodType> foodTypeList = new ArrayList<>();
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

            if (foodTypeView == null) {
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
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_food);

        vmFoodType = ViewModelProviders.of(this).get(ViewModel.class);
        foodTypeList = vmFoodType.getAllFoodTypesList();

        Spinner spinner = findViewById(R.id.spinner_foodtype);
        spinner.setAdapter(baseAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                baseAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("Editor Food: ", "NOThING");
            }
        });


    }
}
