package com.shneddy.dietdiary.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.adapters.ConsumptionAdapter;
import com.shneddy.dietdiary.entity.DiaryEntry;
import com.shneddy.dietdiary.entity.Diem;
import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.intermediates.EntryAndFoodData;
import com.shneddy.dietdiary.viewmodel.DiemAndMoreViewModel;
import com.shneddy.dietdiary.viewmodel.OperationsViewModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.shneddy.dietdiary.activity.AllEntries.ENTRY_ID;

public class EntryDetail extends AppCompatActivity {

    private OperationsViewModel opsVM;
    public static final String DIEM_ID = "EntryDetail.ID";
    int diemId;
    String diemDate;
    TextView totalSugars, totalFoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_detail);

        totalFoods = findViewById(R.id.textView_consumptions_infoodstotal);
        totalSugars = findViewById(R.id.textView_consumptions_sugars_total);
        setTitle("Date"); // todo get date from previous activity

        Intent intent = getIntent();
        diemId = intent.getIntExtra(ENTRY_ID, -1);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_allconsumptions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);

        final ConsumptionAdapter adapter = new ConsumptionAdapter();
        recyclerView.setAdapter(adapter);

        opsVM = ViewModelProviders.of(this).get(OperationsViewModel.class);
        opsVM.getAllEntriesByDiemId(diemId).observe(this, new Observer<List<DiaryEntry>>() {
            @Override
            public void onChanged(List<DiaryEntry> diaryEntries) {
                List<DiaryEntry> unpreppedList = diaryEntries;
                List<EntryAndFoodData> intermediateList = new ArrayList<>();
                DecimalFormat decimalFormat = new DecimalFormat("####.#");
                for (DiaryEntry d : unpreppedList){
                    EntryAndFoodData collectiveData = new EntryAndFoodData();
                    int foodId = d.getFoodId();

                    Food tempFood = opsVM.getFoodById(foodId);

                    collectiveData.setPortionSize(d.getPortionSize());
                    collectiveData.setName(tempFood.getName());
                    collectiveData.setId(d.getId());
                    collectiveData.setFoodId(foodId);
                    collectiveData.setCalcSugars(d.getPortionSize() * tempFood.getGramsSugar());
                    intermediateList.add(collectiveData);
                }
                adapter.setEntryAndFoodsList(intermediateList);

                totalSugars.setText(String.valueOf(decimalFormat.format(adapter.getDailyTotal()))+"g");
                totalFoods.setText("consumed in " + adapter.getItemCount() + " food(s)");
            }
        });


        // Floating action button to add new food
        FloatingActionButton fab = findViewById(R.id.fab_add_consumption);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EntryDetail.this, EditorConsumption.class);
                intent.putExtra(DIEM_ID, diemId);
                startActivity(intent);
            }
        });
    }
}
