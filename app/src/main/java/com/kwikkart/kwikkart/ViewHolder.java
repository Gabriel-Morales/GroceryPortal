package com.kwikkart.kwikkart;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 *  This class is intended to represent the items that will be held within the recycler view.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private TextView name;
    private TextView price;
    private TextView onSale;

    public ViewHolder(View view)
    {
        super(view);
    }


}
