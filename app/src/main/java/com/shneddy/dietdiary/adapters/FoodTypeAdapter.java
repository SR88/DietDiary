package com.shneddy.dietdiary.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.entity.FoodType;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/**
 * Created By Seth Sneddon Mar 2019
 */
public class FoodTypeAdapter extends RecyclerView.Adapter<FoodTypeAdapter.FoodTypeHolder> {

    private List<FoodType> foodTypes = new ArrayList<>();

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
    public FoodTypeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_foodtype, parent, false);

        return new FoodTypeHolder(itemView);
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
    public void onBindViewHolder(@NonNull FoodTypeHolder holder, int position) {
        FoodType currentFoodType = foodTypes.get(position);
        holder.name.setText(currentFoodType.getType());
        holder.description.setText(currentFoodType.getDescription());
    }


    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return foodTypes.size();
    }

    /**
     * Returns EntryAndFoodData at position
     * @param position
     * @return
     */
    public FoodType getFoodTypeAt(int position){
        return foodTypes.get(position);
    }

    /**
     * Sets up the adapter with a list of data
     * @param foodTypes data input into adapter
     */
    public void setFoodTypes(List<FoodType> foodTypes){
        this.foodTypes = foodTypes;
        notifyDataSetChanged();
    }

    public class FoodTypeHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView description;
        public RelativeLayout viewBackground, viewForeground;

        /**
         * Setups up view in list items
         * @param itemView
         */
        public FoodTypeHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textview_foodtypename);
            description = itemView.findViewById(R.id.textview_foodtypedescription);
            viewBackground = itemView.findViewById(R.id.layout_single_consumption_background);
            viewForeground = itemView.findViewById(R.id.layout_single_foodtype_foreground);

        }
    }
}
