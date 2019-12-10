package com.kwikkart.kwikkart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kwikkart.kwikkart.model.Item;

import java.util.ArrayList;

public class SearchItemAdapter  extends RecyclerView.Adapter<ViewHolder> {

    private int itemCount;
    private ArrayList<Item> allDatabaseItems;

    /**
     * SearchItemAdapter
     * @param itemCount int
     * @param allItems ArrayList<Item>
     */
    public SearchItemAdapter(int itemCount, ArrayList<Item> allItems)
    {
        this.itemCount = itemCount;
        allDatabaseItems = allItems;
    }

    /**
     * onCreateViewHolder
     * @param parent @NonNull ViewGroup
     * @param viewType int
     * @return ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * onBindViewHolder
     * @param holder @NonNull ViewHolder
     * @param position int
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item currentItem = allDatabaseItems.get(position);
        Item item = new Item(currentItem.getName(), currentItem.getPrice(), currentItem.getImage());
        holder.initializeView(item.getName(), item.getPrice(), item.getImage(), item);
    }

    /**
     * getItemCount
     * @return int
     */
    @Override
    public int getItemCount() {
        return itemCount;
    }



}
