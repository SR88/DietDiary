package com.shneddy.dietdiary.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.activity.EditorFood;
import com.shneddy.dietdiary.entity.Food;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodHolder>{

    private List<Food> foodList = new ArrayList<>();
    private AdapterView.OnItemClickListener clickListener;


    @NonNull
    @Override
    public FoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_food, parent, false);
        return new FoodHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodHolder holder, int position) {
        Food data = foodList.get(position);
        holder.name.setText(data.getName());
        holder.sugars.setText(String.valueOf(data.getGramsSugar()));
        holder.foodType.setText(" ");
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public void setFoods(List<Food> list){
        foodList = list;
        notifyDataSetChanged();
    }




    public Food getFoodAt(int pos){
        return foodList.get(pos);
    }

    public class FoodHolder extends RecyclerView.ViewHolder{
        private TextView name, sugars, foodType;
        public RelativeLayout viewBackground, viewForeground;

        public FoodHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textview_food_name);
            sugars = itemView.findViewById(R.id.textview_food_grams_sugar);
            foodType = itemView.findViewById(R.id.textview_food_foodtype);
            viewBackground = itemView.findViewById(R.id.layout_single_food_background);
            viewForeground = itemView.findViewById(R.id.layout_single_food_foreground);
        }
    }

}
