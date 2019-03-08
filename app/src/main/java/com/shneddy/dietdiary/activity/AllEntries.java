package com.shneddy.dietdiary.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.entity.Diem;
import com.shneddy.dietdiary.viewmodel.OperationsViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AllEntries extends AppCompatActivity {

    public static final String ENTRY_DATE = "package com.shneddy.dietdiary.activity.ID";
    public static final int ADD_ENTRY_REQUEST = 1;
    public static final int EDIT_FOOD_REQUEST = 2;
    private static final int DATE_DILOG = 0;
    private int mYear, mDay, mMonth;
    private String dateString, formattedDate;
    private OperationsViewModel opsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_entries);

        setTitle("All Entries");
        setCalendar();

        opsVM = ViewModelProviders.of(this).get(OperationsViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.recyclerview_allentries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);

        // Floating action button a new data
        FloatingActionButton fab = findViewById(R.id.fab_add_date);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create popup for date
                showDialog(DATE_DILOG);
                DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy");
                SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy MMM dd");

                try {
                    Date date = originalFormat.parse(dateString);
                    formattedDate = targetFormat.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (opsVM.getDiemByDate(formattedDate).size() > 0){
                    createErrorDialog(1);
                } else {
                    Diem diem = new Diem(formattedDate);
                    opsVM.insertDiem(diem);
                }
            }
        });

    }



    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DATE_DILOG){
            return new DatePickerDialog(this, datePickerListener, mYear, mMonth, mDay);
        }
        return null;
    }

    private void setCalendar() {
        final Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH);
        mDay = cal.get(Calendar.DAY_OF_MONTH);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mMonth = month + 1;
            mYear = year;
            mDay = dayOfMonth;

            dateString = mMonth + "/" + mDay + "/" + mYear;
        }
    };

    private void createErrorDialog(int i) {
        if (i == 1){
            AlertDialog.Builder builder = new AlertDialog.Builder(AllEntries.this);
            builder.setMessage("This date already exists in your entries list.\n\nPlease access and use it to add more diet food entries.")
                    .setCancelable(false)
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.setTitle("Date already exists!");
            dialog.show();
        }
    }
}
