package com.kwikkart.kwikkart.model;

/**
 * Item class handles function dealing with an Item object
 */
public class Item {

    private String name;
    private String price;
    private String image;

    /**
     * Item
     * default constructor
     */
    public Item()
    {
        // Default constructor required for calls to DataSnapshot
    }

    /**
     * Item
     * initialized constructor
     * @param name String
     * @param price String
     * @param image String
     */
    public Item(String name, String price, String image)
    {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    /**
     * getName
     * @return String
     */
    public String getName()
    {
        return name;
    }

    /**
     * getPrice
     * @return String
     */
    public String getPrice()
    {
        return price;
    }

    /**
     * getImage
     * @return String
     */
    public String getImage()
    {
        return image;
    }

    /**
     * setName
     * @param name String
     */
    public void setName(String name)
    {
            this.name = name;
    }

    /**
     *  setPrice
     * @param price String
     */
    public void setPrice(String price)
    {
            this.price = price;
    }

    /**
     * setImage
     * @param image String
     */
    public void setImage(String image)
    {
        this.image = image;
    }

}
