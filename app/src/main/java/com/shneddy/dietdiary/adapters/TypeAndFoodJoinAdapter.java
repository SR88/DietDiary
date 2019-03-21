package com.shneddy.dietdiary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.intermediates.FoodAndTypeData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/**
 * Created By Seth Sneddon Mar 2019
 */
public class TypeAndFoodJoinAdapter extends RecyclerView.Adapter<TypeAndFoodJoinAdapter.TypeFoodHolder>{

    private List<FoodAndTypeData> flatList = new ArrayList<>();

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @NonNull
    @Override
    public TypeFoodHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_food, parent, false);

        return new TypeFoodHolder(itemView);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link ViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull TypeFoodHolder holder, int position) {
        FoodAndTypeData data = flatList.get(position);
        holder.name.setText(data.getName());
        holder.sugars.setText(String.valueOf(data.getGramsSugar()) + " grams sugar per serving.");
        holder.foodType.setText(data.getFoodType());
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return flatList.size();
    }

    /**
     * Returns EntryAndFoodData at position
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return flatList.get(position).getId();
    }

    /**
     * Returns Compound Class object at position
     * @param pos
     * @return compound object
     */
    public FoodAndTypeData getFoodAndType(int pos){
        return flatList.get(pos);
    }

    /**
     * Sets up the adapter with a list of data
     * @param typeAndFoods data input into adapter
     */
    public void setList(List<FoodAndTypeData> typeAndFoods) {
        flatList = typeAndFoods;
        notifyDataSetChanged();
    }


    public class TypeFoodHolder extends RecyclerView.ViewHolder{
        private TextView name, sugars, foodType;
        public RelativeLayout viewBackground, viewForeground;

        /**
         * Setups up view in list items
         * @param itemView
         */
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
