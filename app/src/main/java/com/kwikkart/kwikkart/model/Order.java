package com.kwikkart.kwikkart.model;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private String orderDate;
    private String deliverDate;
    private String itemsOrdered;
    private String pricePaid;

    public Order()
    {}


    public Order(String orderDate, String deliverDate, String itemsOrdered, String pricePaid){
      this.orderDate = orderDate;
      this.deliverDate = deliverDate;
      this.itemsOrdered = itemsOrdered;
      this.pricePaid = pricePaid;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> orderValues = new HashMap<>();
        orderValues.put("orderDate", this.orderDate);
        orderValues.put("deliverDate", this.deliverDate);
        orderValues.put("itemsOrdered", this.itemsOrdered);
        orderValues.put("pricePaid", this.pricePaid);

        return orderValues;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getItemsOrdered() {
        return itemsOrdered;
    }

    public void setItemsOrdered(String itemsOrdered) {
        this.itemsOrdered = itemsOrdered;
    }

    public String getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(String pricePaid) {
        this.pricePaid = pricePaid;
    }
}
