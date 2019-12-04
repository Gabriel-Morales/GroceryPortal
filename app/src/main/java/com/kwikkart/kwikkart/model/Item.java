package com.kwikkart.kwikkart.model;

public class Item {

    private String name;
    private String price;
    private boolean isOnSale;

    public Item()
    {
        // Default constructor required for calls to DataSnapshot
    }

    public Item(String name, String price, boolean isOnSale)
    {
        this.name = name;
        this.price = price;
        this.isOnSale = isOnSale;
    }

}
