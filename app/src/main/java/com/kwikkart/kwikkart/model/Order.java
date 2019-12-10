package com.kwikkart.kwikkart.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Order class handles functions dealing with Order objects
 */
public class Order {

    private String orderDate;
    private String deliverDate;
    private String itemsOrdered;
    private String pricePaid;

    /**
     * Order
     * default constructor
     */
    public Order()
    {}

    /**
     * Order
     * initialized constructor
     * @param orderDate String
     * @param deliverDate String
     * @param itemsOrdered String
     * @param pricePaid String
     */
    public Order(String orderDate, String deliverDate, String itemsOrdered, String pricePaid){
      this.orderDate = orderDate;
      this.deliverDate = deliverDate;
      this.itemsOrdered = itemsOrdered;
      this.pricePaid = pricePaid;
    }

    /**
     * toMap
     * @return Map<String, Object>
     */
    public Map<String, Object> toMap(){
        Map<String, Object> orderValues = new HashMap<>();
        orderValues.put("orderDate", this.orderDate);
        orderValues.put("deliverDate", this.deliverDate);
        orderValues.put("itemsOrdered", this.itemsOrdered);
        orderValues.put("pricePaid", this.pricePaid);

        return orderValues;
    }

    /**
     * getOrderDate
     * @return String
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * setOrderDate
     * @param orderDate String
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * getDeliverDate
     * @return String
     */
    public String getDeliverDate() {
        return deliverDate;
    }

    /**
     * setDeliverDate
     * @param deliverDate String
     */
    public void setDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate;
    }

    /**
     * getItemsOrdered
     * @return String
     */
    public String getItemsOrdered() {
        return itemsOrdered;
    }

    /**
     * setItemsOrdered
     * @param itemsOrdered String
     */
    public void setItemsOrdered(String itemsOrdered) {
        this.itemsOrdered = itemsOrdered;
    }

    /**
     * getPricePaid
     * @return String
     */
    public String getPricePaid() {
        return pricePaid;
    }

    /**
     * setPricePaid
     * @param pricePaid String
     */
    public void setPricePaid(String pricePaid) {
        this.pricePaid = pricePaid;
    }
}
