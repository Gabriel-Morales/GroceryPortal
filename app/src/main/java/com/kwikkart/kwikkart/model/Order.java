package com.kwikkart.kwikkart.model;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private String dateOfOrder;
    private String dateOfDelivery;
    private int itemsOrdered;

    public Order(String dateOfOrder, String dateOfDelivery, int itemsOrdered){
      this.dateOfOrder = dateOfOrder;
      this.dateOfDelivery = dateOfDelivery;
      this.itemsOrdered = itemsOrdered;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> orderValues = new HashMap<>();
        orderValues.put("order_date", this.dateOfOrder);
        orderValues.put("deliver_date", this.dateOfDelivery);
        orderValues.put("items_ordered", this.itemsOrdered);


        return orderValues;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public String getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(String dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public int getItemsOrdered() {
        return itemsOrdered;
    }

    public void setItemsOrdered(int itemsOrdered) {
        this.itemsOrdered = itemsOrdered;
    }
}
