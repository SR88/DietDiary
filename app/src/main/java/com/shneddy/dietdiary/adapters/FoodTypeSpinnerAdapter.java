package com.shneddy.dietdiary.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.entity.FoodType;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FoodTypeSpinnerAdapter extends ArrayAdapter<FoodType> {

    private List<FoodType> foodTypeList;
    private LayoutInflater inflater;
    private Context context;

    public FoodTypeSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<FoodType> objects) {
        super(context, R.layout.support_simple_spinner_dropdown_item, R.id.spinner_foodtype, objects);
        this.context = context;
        this.foodTypeList = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int pos, View convertView, ViewGroup parent){
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.support_simple_spinner_dropdown_item, parent, false);

        TextView textView = row.findViewById(R.id.spinner_foodtype);
        textView.setText(foodTypeList.get(pos).getType());

        return row;
    }
}
