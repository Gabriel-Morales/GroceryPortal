package com.kwikkart.kwikkart.model;

public class Item {

    private String name;
    private String price;
    private String image;

    public Item()
    {
        // Default constructor required for calls to DataSnapshot
    }

    public Item(String name, String price, String image)
    {
        this.name = name;
        this.price = price;
        this.image = image;
    }


    public String getName()
    {
        return name;
    }

    public String getPrice()
    {
        return price;
    }

    public String getImage()
    {
        return image;
    }

    public void setName(String name)
    {
            this.name = name;
    }

    public void setPrice(String price)
    {
            this.price = price;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

}
