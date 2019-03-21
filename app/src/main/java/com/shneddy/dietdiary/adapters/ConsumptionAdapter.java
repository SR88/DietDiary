package com.shneddy.dietdiary.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shneddy.dietdiary.R;
import com.shneddy.dietdiary.intermediates.EntryAndFoodData;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
/**
 * Created By Seth Sneddon Mar 2019
 */
public class ConsumptionAdapter extends RecyclerView.Adapter<ConsumptionAdapter.ConsumptionHolder>{

    private List<EntryAndFoodData> officialList = new ArrayList<>();

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
    public ConsumptionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_consumption, parent, false);

        return new ConsumptionHolder(itemView);
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
    public void onBindViewHolder(@NonNull ConsumptionHolder holder, int position) {
        DecimalFormat decimalFormat = new DecimalFormat("####.#");
        EntryAndFoodData data = officialList.get(position);
        holder.name.setText(data.getName());
        holder.portionSize.setText("in " +String.valueOf(data.getPortionSize()) + " serving(s)");
        holder.sugarsCalc.setText(decimalFormat.format(data.getCalcSugars()) +"g");
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return officialList.size();
    }


    /**
     * Returns EntryAndFoodData at position
     * @param pos
     * @return
     */
    public EntryAndFoodData getEntryAndFoodAt(int pos){
        return officialList.get(pos);
    }

    /**
     * Sets up the adapter with a list of data
     * @param list data input into adapter
     */
    public void setEntryAndFoodsList(List<EntryAndFoodData> list){
        this.officialList = list;
        notifyDataSetChanged();
    }

    /**
     * Calculates total of sugars for a day
     * @return total sugars
     */
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

        /**
         * Setups up view in list items
         * @param itemView
         */
        public ConsumptionHolder(@NonNull View itemView) {
            super(itemView);
            sugarsCalc = itemView.findViewById(R.id.textView_consumptions_sugars);
            name = itemView.findViewById(R.id.textView_consumption_fooditem);
            portionSize = itemView.findViewById(R.id.textView_consumption_infoods);
            viewBackground = itemView.findViewById(R.id.layout_single_consumption_background);
            viewForeground = itemView.findViewById(R.id.layout_single_consumption_foreground);
        }
    }
}
