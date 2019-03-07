package com.shneddy.dietdiary.adapters;

import android.annotation.SuppressLint;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// https://stackoverflow.com/questions/44961723/how-to-represent-nested-one-to-many-relationship-in-android-room


public class ComplexFoodAndTypeAdapter extends RecyclerView.Adapter<ComplexFoodAndTypeAdapter.FoodAndTypeHolder> {

    private List<FoodAndType> list = new ArrayList<>();
    private List<Food> foodList = new ArrayList<>();
    private List<FoodAndTypeData> foodAndTypeDataList = new ArrayList<>();

    @NonNull
    @Override
    public FoodAndTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_food, parent, false);

        return new FoodAndTypeHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull FoodAndTypeHolder holder, int position) {
        FoodAndTypeData data = foodAndTypeDataList.get(position);
        holder.name.setText(data.getName());
        holder.sugars.setText(String.valueOf(data.getGramsSugar()));
        holder.foodType.setText(data.getFoodType());
    }

    @Override
    public int getItemCount() {
        return foodAndTypeDataList.size();
    }

    @Override
    public long getItemId(int position) {
        return foodAndTypeDataList.get(position).getId();
    }

    public FoodAndTypeData getFoodAndTypeAt(int position) {
        return foodAndTypeDataList.get(position);
    }

    public void setFoodAndTypes(List<FoodAndType> foodtypes) {
        this.list = foodtypes;
        notifyDataSetChanged();

        int id;
        String foodType;
        for (FoodAndType foodAndType : list) {
            FoodAndTypeData data = new FoodAndTypeData();

            id = foodAndType.foodType.getId();
            foodType = foodAndType.foodType.getType();

            for (Food f : foodAndType.food){
                data.setFoodTypeId(id);
                data.setFoodType(foodType);
                data.setId(f.getId());
                data.setName(f.getName());
                data.setGramsSugar(f.getGramsSugar());
                foodAndTypeDataList.add(data);
                data = new FoodAndTypeData();
            }

        }
//        for (int i = 0; i > foodAndTypeDataList.size(); i ++) {
//            Log.d("Complex Adapter foodanddatatype list", foodAndTypeDataList.get(i).toString());
//        }
    }

    public void setFoods(List<Food> foods) {
        foodList = foods;
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



