package com.kwikkart.kwikkart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.kwikkart.kwikkart.model.Item;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private Context context;
    private Toolbar parentToolbar;

    public CartAdapter(Context context, Toolbar parentToolbar)
    {
        this.context = context;
        this.parentToolbar = parentToolbar;
    }


    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);
        CartViewHolder cvh = new CartViewHolder(view);
        cvh.setParentAdapter(this);
        cvh.setParentToolbar(parentToolbar);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Item item = HomeFragment.getCart().get(position);
        holder.initializeView(item.getName(), item.getPrice(), item.getImage(), item);
    }

    @Override
    public int getItemCount() {
        return HomeFragment.getCart().size();
    }

}
