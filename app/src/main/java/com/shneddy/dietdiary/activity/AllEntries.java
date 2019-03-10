package com.shneddy.dietdiary.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.adapters.DiemAdapter;
import com.shneddy.dietdiary.entity.Diem;
import com.shneddy.dietdiary.entity.DiemAndEntry;
import com.shneddy.dietdiary.viewmodel.DiemAndMoreViewModel;
import com.shneddy.dietdiary.viewmodel.OperationsViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AllEntries extends AppCompatActivity {

    public static final String ENTRY_DATE = "package com.shneddy.dietdiary.activity.DATE";
    public static final String ENTRY_ID = "package com.shneddy.dietdiary.activity.ID";
    public static final int EDIT_FOOD_REQUEST = 2;
    private static final int DATE_DILOG = 0;
    private int mYear, mDay, mMonth;
    private String dateString, formattedDate;
    private OperationsViewModel opsVM;
    private DiemAndMoreViewModel diemVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_entries);

        setTitle("All Entries");
        setCalendar();

        RecyclerView recyclerView = findViewById(R.id.recyclerview_allentries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);

        final DiemAdapter adapter = new DiemAdapter();
        recyclerView.setAdapter(adapter);

        opsVM = ViewModelProviders.of(this).get(OperationsViewModel.class);
        opsVM.getAllDiems().observe(this, new Observer<List<Diem>>() {
            @Override
            public void onChanged(List<Diem> diems) {
                adapter.setDiemList(diems);
            }
        });

        // Floating action button a new data
        FloatingActionButton fab = findViewById(R.id.fab_add_date);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create popup for date
                showDialog(DATE_DILOG);
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
                if (direction == ItemTouchHelper.LEFT) { // delete

                    Diem tempDiem = adapter.getDiemAt(viewHolder.getAdapterPosition());

                    List<DiemAndEntry> checkList = opsVM.getDiemById(tempDiem.getId());

                    if(checkList.get(0).relDiaryList.size() > 0){
                        // todo create popup and cascade delete if true
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(AllEntries.this);
                        alertBuilder.setMessage("There are Foods attached to this Food Type. If you choose to delete this Food Type, the Foods associated with this type and their entries in your diary will also be deleted!")
                                .setCancelable(true)
                                .setPositiveButton("Delete Food Type", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        deleteEntry(checkList.get(0).diem.getId());
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        adapter.notifyItemChanged(viewHolder.getAdapterPosition());
                                    }
                                });
                        AlertDialog alertDialog = alertBuilder.create();
                        alertDialog.setTitle("Attached Foods Detected!");
                        alertDialog.show();
                    } else {
                        deleteEntry(checkList.get(0).diem.getId());
                    }
                }
                if (direction == ItemTouchHelper.RIGHT) { // edit
                    Diem editedDiem = adapter
                            .getDiemAt(viewHolder.getAdapterPosition());
                    Intent intent = new Intent(AllEntries.this, EntryDetail.class); // todo change where this goes
                    intent.putExtra(ENTRY_DATE, editedDiem.getDate());
                    intent.putExtra(ENTRY_ID, editedDiem.getId());
                    startActivityForResult(intent, EDIT_FOOD_REQUEST);
                }

            }


            @Override
            public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                        RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                        int actionState, boolean isCurrentlyActive) {

                View foregroundView = ((DiemAdapter.DiemHolder) viewHolder).viewForeground;

                getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY, actionState,
                        isCurrentlyActive);
            }

            @Override
            public void clearView(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder) {
                final View foregroundView = ((DiemAdapter.DiemHolder) viewHolder)
                        .viewForeground;
                getDefaultUIUtil().clearView(foregroundView);
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {
                final View foregroundView = ((DiemAdapter.DiemHolder) viewHolder)
                        .viewForeground;

                View backgroundView = ((DiemAdapter.DiemHolder) viewHolder)
                        .viewBackground;

                TextView textViewDelete = recyclerView.findViewHolderForAdapterPosition(viewHolder.getAdapterPosition()).itemView.findViewById(R.id.textview_delete_consumption);
                TextView textViewEdit = recyclerView.findViewHolderForAdapterPosition(viewHolder.getAdapterPosition()).itemView.findViewById(R.id.textview_edit_consumption);
                ImageView iconDelete = recyclerView.findViewHolderForAdapterPosition(viewHolder.getAdapterPosition()).itemView.findViewById(R.id.icon_delete_entry);
                ImageView iconEdit = recyclerView.findViewHolderForAdapterPosition(viewHolder.getAdapterPosition()).itemView.findViewById(R.id.icon_edit_consumption);

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

    private void deleteEntry(int id) {
        Diem diemToDelete = new Diem("x");
        diemToDelete.setId(id);
        opsVM.deleteDiem(diemToDelete);
        Toast.makeText(AllEntries.this, "Date was deleted.",
                Toast.LENGTH_SHORT).show();
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
