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


    public String getName()
    {
        return name;
    }

    public String getPrice()
    {
        return price;
    }

    public boolean isOnSale()
    {
        return isOnSale;
    }

    public void setName(String name)
    {
            this.name = name;
    }

    public void setPrice(String price)
    {
            this.price = price;
    }

    public void setIsOnSale(boolean isOnSale)
    {
        this.isOnSale = isOnSale;
    }

}
