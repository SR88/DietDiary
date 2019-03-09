package com.shneddy.dietdiary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.entity.Diem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DiemAdapter extends RecyclerView.Adapter<DiemAdapter.DiemHolder> {

    private List<Diem> list = new ArrayList<>();

    @NonNull
    @Override
    public DiemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_entry, parent, false);
        return new DiemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DiemHolder holder, int position) {
        Diem diem = list.get(position);
        holder.date.setText(diem.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Diem getDiemAt(int position){
        return list.get(position);
    }

    public void setDiemList(List<Diem> list){
        List<Diem> tempList = list;

        // sort by date descending
        Collections.sort(tempList, new Comparator<Diem>() {
            @Override
            public int compare(Diem o1, Diem o2) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");

                Date date1, date2;

                try {
                    date1 = sdf.parse(o1.getDate());
                    o1.setDiemDate(date1);
                    date2 = sdf.parse(o2.getDate());
                    o2.setDiemDate(date2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                return o1.getDiemDate().compareTo(o2.getDiemDate());
            }
        });
        Collections.reverse(tempList);

        this.list = tempList;
        notifyDataSetChanged();
    }

    public class DiemHolder extends RecyclerView.ViewHolder{
        private TextView date;
        public RelativeLayout viewBackground, viewForeground;

        public DiemHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.textView_consumption_date);
            viewBackground = itemView.findViewById(R.id.layout_single_diem_background);
            viewForeground = itemView.findViewById(R.id.layout_single_consumption_foreground);
        }
    }
}
