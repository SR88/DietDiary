package com.shneddy.dietdiary.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.entity.FoodAndType;
import com.shneddy.dietdiary.entity.FoodAndTypeData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ComplexFoodAndTypeAdapter extends RecyclerView.Adapter<ComplexFoodAndTypeAdapter.FoodAndTypeHolder> {

    private List<FoodAndType> list = new ArrayList<>();

    @NonNull
    @Override
    public FoodAndTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_food, parent, false);

        return new FoodAndTypeHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull FoodAndTypeHolder holder, int position) {
        FoodAndType currentFoodAndType = list.get(position);
        holder.name.setText(currentFoodAndType.foodList.get(0).getName());
        holder.sugars.setText("Grams sugar per serving: " + String.valueOf(currentFoodAndType
                .foodList.get(0).getGramsSugar()));
        if (Integer.valueOf(currentFoodAndType.foodList.get(0).getFoodTypeId()) != null) {
            holder.foodType.setText(String.valueOf(currentFoodAndType.foodList.get(0).getFoodTypeId()));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).foodList.get(0).getId();
    }

    public ComplexFoodAndTypeAdapter() {

        setHasStableIds(true);
    }

    public FoodAndType getFoodAndTypeAt(int position) {
        return list.get(position);
    }

    public void setFoodAndTypes(List<FoodAndType> foodtypes) {
        this.list = foodtypes;
        notifyDataSetChanged();
    }


    public class FoodAndTypeHolder extends RecyclerView.ViewHolder {
        private TextView name, sugars, foodType;
        public RelativeLayout viewBackground, viewForeground;

        public FoodAndTypeHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textview_food_name);
            sugars = itemView.findViewById(R.id.textview_food_grams_sugar);
            foodType = itemView.findViewById(R.id.textview_food_foodtype);
            viewBackground = itemView.findViewById(R.id.layout_single_food_background);
            viewForeground = itemView.findViewById(R.id.layout_single_food_foreground);
        }
    }
}
