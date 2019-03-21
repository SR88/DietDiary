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
/**
 * Created By Seth Sneddon Mar 2019
 */
public class EditorConsumption extends AppCompatActivity {

    private int diemId;
    private OperationsViewModel opsVM;
    private List<Food> foodList = new ArrayList<>();
    private Spinner spinner;
    private EditText portionSize;
    private Intent intent;

    /*
        This is an adapter for the spinner in the activity.  It allows for the appropriate view
        to be displayed.
     */
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

    /**
     * When the screen/activity starts, this method is automatically executed.
     * @param savedInstanceState
     */
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
                saveConsumption();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Handles validation of inputs by the user.  If there are no issues with the user's input
     * it inserts the record.
     */
    private void saveConsumption() {
        String error = "";
        if(portionSize.getText().toString().length() < 1){
            error += "Please fill in the portion size consumed";
        }

        try{
            Double.parseDouble(portionSize.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this,"Please make sure you enter in a proper decimal number",
                    Toast.LENGTH_LONG).show();
            return;
        }

        if(error.length() > 1){
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            return;
        } else {
            DiaryEntry diaryEntry = new DiaryEntry(
                    getSpinnerVal(),
                    Double.parseDouble(portionSize.getText().toString()),
                    diemId);

            opsVM.insertFoodDiary(diaryEntry);
            setResult(RESULT_OK);
            finish();
        }
    }

    /**
     * Gets the value of the spinner's selection.
     * @return food id pkey
     */
    private int getSpinnerVal(){
        Food food = (Food) spinner.getSelectedItem();
        return food.getId();
    }

}
