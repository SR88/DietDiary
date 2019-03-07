package com.shneddy.dietdiary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.entity.Food;
import com.shneddy.dietdiary.entity.FoodAndType;
import com.shneddy.dietdiary.entity.FoodAndTypeData;
import com.shneddy.dietdiary.entity.FoodType;
import com.shneddy.dietdiary.entity.TypeAndFood;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShitAdapter extends RecyclerView.Adapter<ShitAdapter.TypeFoodHolder>{

    private List<FoodAndTypeData> flatList = new ArrayList<>();

    @NonNull
    @Override
    public TypeFoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_food, parent, false);

        return new TypeFoodHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeFoodHolder holder, int position) {
        FoodAndTypeData data = flatList.get(position);
        holder.name.setText(data.getName());
        holder.sugars.setText(String.valueOf(data.getGramsSugar()));
        holder.foodType.setText(data.getFoodType());
    }

    @Override
    public int getItemCount() {
        return flatList.size();
    }

    public void setList(List<FoodAndTypeData> typeAndFoods) {
        flatList = typeAndFoods;
        notifyDataSetChanged();
    }


    public class TypeFoodHolder extends RecyclerView.ViewHolder{
        private TextView name, sugars, foodType;
        public RelativeLayout viewBackground, viewForeground;

        public TypeFoodHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textview_food_name);
            sugars = itemView.findViewById(R.id.textview_food_grams_sugar);
            foodType = itemView.findViewById(R.id.textview_food_foodtype);
            viewBackground = itemView.findViewById(R.id.layout_single_food_background);
            viewForeground = itemView.findViewById(R.id.layout_single_food_foreground);
        }
    }

}
