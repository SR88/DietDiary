package com.shneddy.dietdiary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.intermediates.EntryAndFoodData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ConsumptionAdapter extends RecyclerView.Adapter<ConsumptionAdapter.ConsumptionHolder>{

    private List<EntryAndFoodData> officialList = new ArrayList<>();

    @NonNull
    @Override
    public ConsumptionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_food, parent, false);

        return new ConsumptionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsumptionHolder holder, int position) {
        EntryAndFoodData data = officialList.get(position);
        holder.name.setText(data.getName());
        holder.portionSize.setText("in " +String.valueOf(data.getPortionSize()) + " serving(s)");
        holder.sugarsCalc.setText(String.valueOf(data.getCalcSugars()) +"g");
    }

    @Override
    public int getItemCount() {
        return officialList.size();
    }

    public EntryAndFoodData getEntryAndFoodAt(int pos){
        return officialList.get(pos);
    }

    public void setEntryAndFoodsList(List<EntryAndFoodData> list){
        this.officialList = list;
        notifyDataSetChanged();
    }

    public double getDailyTotal(){
        double total = 0.0;
        for (EntryAndFoodData item : officialList){
            total +=  item.getCalcSugars();
        }
        return total;
    }

    public class ConsumptionHolder extends RecyclerView.ViewHolder{

        private TextView name, sugarsCalc, portionSize;
        public RelativeLayout viewBackground, viewForeground;

        public ConsumptionHolder(@NonNull View itemView) {
            super(itemView);
            sugarsCalc = itemView.findViewById(R.id.textView_consumptions_sugars);
            name = itemView.findViewById(R.id.textView_consumptions_sugars);
            portionSize = itemView.findViewById(R.id.textView_consumptions_sugars);
            viewBackground = itemView.findViewById(R.id.layout_single_consumption_background);
            viewForeground = itemView.findViewById(R.id.layout_single_consumption_foreground);
        }
    }
}
