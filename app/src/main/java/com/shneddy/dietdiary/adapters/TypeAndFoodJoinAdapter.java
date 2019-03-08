package com.shneddy.dietdiary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.intermediates.FoodAndTypeData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TypeAndFoodJoinAdapter extends RecyclerView.Adapter<TypeAndFoodJoinAdapter.TypeFoodHolder>{

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
        holder.sugars.setText(String.valueOf(data.getGramsSugar()) + " grams sugar per serving.");
        holder.foodType.setText(data.getFoodType());
    }

    @Override
    public int getItemCount() {
        return flatList.size();
    }

    @Override
    public long getItemId(int position) {
        return flatList.get(position).getId();
    }

    public FoodAndTypeData getFoodAndType(int pos){
        return flatList.get(pos);
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
