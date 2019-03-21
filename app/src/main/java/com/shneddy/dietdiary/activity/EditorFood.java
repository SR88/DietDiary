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

import com.shneddy.dietdiary.viewmodel.OperationsViewModel;
import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.entity.FoodType;
import java.util.ArrayList;
import java.util.List;
/**
 * Created By Seth Sneddon Mar 2019
 */
public class EditorFood extends AppCompatActivity {

    public static final String EXTRA_FOOD_FOODTYPE_ID =
            "package com.shneddy.dietdiary.activity.FOODTYPE_ID";
    public static final String EXTRA_FOOD_FOOD_NAME =
            "package com.shneddy.dietdiary.activity.FOOD_NAME";
    public static final String EXTRA_FOOD_SUGARS =
            "package com.shneddy.dietdiary.activity.FOOD_SUGARS";
    public static final String  EXTRA_FOOD_ID =
            "package com.shneddy.dietdiary.activity.FOOD_ID";

    private int previousId, editSelectedFoodType;
    private List<FoodType> foodTypeList = new ArrayList<>();
    OperationsViewModel operationsViewModel;
    private Spinner spinner;
    private EditText etName, etSugar;
    private Intent intent;

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

    /**
     * When the screen/activity starts, this method is automatically executed.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_food);

        intent = getIntent();

        setTitle("New Food");
        this.etName = findViewById(R.id.edittext_foodname);
        this.etSugar = findViewById(R.id.edittext_gramssugar);
        this.spinner = findViewById(R.id.spinner_foodtype);

        operationsViewModel = ViewModelProviders.of(this).get(OperationsViewModel.class);
        foodTypeList = operationsViewModel.getAllFoodTypesList();

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

        /*
            Check to see if this is an edit request, if true, sets the values for the food that
            is being edited
        */
        if(intent.hasExtra(AllFoods.FOOD_ID)){
            setTitle("Edit your Food");
            previousId = intent.getIntExtra(AllFoods.FOOD_ID, -1);
            etName.setText(intent.getStringExtra(AllFoods.FOOD_NAME));
            etSugar.setText(String.valueOf(intent
                    .getDoubleExtra(AllFoods.FOOD_SUGAR, 5.5)));
            int foodTypeId = intent.getIntExtra(AllFoods.FOOD_FOODTYPE,0);
            if (foodTypeId != -1){
                for (FoodType f : foodTypeList){
                    if (f.getId() == foodTypeId){
                        editSelectedFoodType = foodTypeList.indexOf(f);
                    }
                }
                spinner.setSelection(editSelectedFoodType);
            }
        }
    }

    /**
     * Setups of the menu icon at the top right of the screen to save the item which is being
     * edited or created
     * @param menu which menu on the screen
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_basic_save, menu);
        return true;
    }

    /**
     * This directs what the application to do based on which item in the menu at the top right
     * of the screen is selected.  At this moment there is only the save option
     * @param item in menu selected
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_save:
                saveFood();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Handles validation of inputs by the user.  It passes the data back to the previous activity
     * which called this activity to either insert or update the appropriate record.
     */
    private void saveFood() {
        String name = etName.getText().toString();
        Double sugars;
        try {
            Double.parseDouble(etSugar.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter in a valid decimal number for your sugar value", Toast.LENGTH_LONG).show();
            return;
        }
        sugars = Double.parseDouble(etSugar.getText().toString());

        String error = "";

        if (name.trim().isEmpty()){
            error += "Please make sure you have a name for your food. \n";
        }
        if (sugars == null) {
            error += "Please fill in a sugar value.";
        }
        if (error.length() > 1){
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            return;
        } else { // return data to appropriate activity with valid information
            Intent dataReturn = new Intent();
            if (previousId > 0){
                dataReturn.putExtra(EXTRA_FOOD_ID, previousId);
            }
            dataReturn.putExtra(EXTRA_FOOD_FOOD_NAME, name);
            dataReturn.putExtra(EXTRA_FOOD_SUGARS, sugars);
            dataReturn.putExtra(EXTRA_FOOD_FOODTYPE_ID, getSpinnerVal());
            setResult(RESULT_OK, dataReturn);
            finish();
        }
    }

    /**
     * Gets the value of the spinner's selection.
     * @return food id pkey
     */
    private int getSpinnerVal() {
        FoodType foodtype = (FoodType) spinner.getSelectedItem();
        return foodtype.getId();
    }
}
