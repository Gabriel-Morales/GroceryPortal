package com.kwikkart.kwikkart;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class TrackViewHolder extends RecyclerView.ViewHolder {

    private TextView totalPaid;
    private TextView deliveryDate;
    private TextView orderDate;

    /**
     * TrackViewHolder
     * @param itemView @NonNull View
     */
    public TrackViewHolder(@NonNull View itemView) {
        super(itemView);
        totalPaid = itemView.findViewById(R.id.itemtotal);
        deliveryDate = itemView.findViewById(R.id.deliveryDate);
        orderDate = itemView.findViewById(R.id.orderDate);
    }

    /**
     * initializeView
     * @param paid String
     * @param deliveryDate String
     * @param orderDate String
     * @param items String
     */
    public void initializeView(String paid, String deliveryDate, String orderDate, String items)
    {
        this.totalPaid.setText("$"+paid + " for " + items + " items");
        this.deliveryDate.setText(deliveryDate);
        this.orderDate.setText(orderDate);
    }

}
