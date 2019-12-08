package com.kwikkart.kwikkart.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Order {

    private List<Item> orderItems;
    private double totalPayment;
    private String dateOfOrder;

    public Order(String dateOfOrder){
        this.orderItems = new ArrayList<>();
        this.dateOfOrder = dateOfOrder;
    }
/*
Might need toMap to make easy to store in dbgi
    public Map<String, Object> toMap(){
    }

 */
}
