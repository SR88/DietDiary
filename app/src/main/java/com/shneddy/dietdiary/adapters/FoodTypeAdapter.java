package com.shneddy.dietdiary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.entity.FoodType;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodTypeAdapter extends RecyclerView.Adapter<FoodTypeAdapter.FoodTypeHolder> {

    private List<FoodType> foodTypes = new ArrayList<>();

    @NonNull
    @Override
    public FoodTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_foodtype, parent, false);

        return new FoodTypeHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull FoodTypeHolder holder, int position) {
        FoodType currentFoodType = foodTypes.get(position);
        holder.name.setText(currentFoodType.getType());
        holder.description.setText(currentFoodType.getDescription());
    }


    @Override
    public int getItemCount() {
        return foodTypes.size();
    }

    public FoodType getFoodTypeAt(int position){
        return foodTypes.get(position);
    }

    public void setFoodTypes(List<FoodType> foodTypes){
        this.foodTypes = foodTypes;
        notifyDataSetChanged();
    }

    public class FoodTypeHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView description;
        public RelativeLayout viewBackground, viewForeground;

        public FoodTypeHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textview_foodtypename);
            description = itemView.findViewById(R.id.textview_foodtypedescription);
            viewBackground = itemView.findViewById(R.id.layout_single_consumption_background);
            viewForeground = itemView.findViewById(R.id.layout_single_foodtype_foreground);

        }
    }
}
