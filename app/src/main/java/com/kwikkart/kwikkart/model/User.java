package com.kwikkart.kwikkart.model;

public class User {

    private String email;
    private String name;
    private String billingAddress;


    public User()
    {
        // Default constructor required for calls to DataSnapshot
    }

    public User(String email, String name, String billingAddress)
    {
        this.email = email;
        this.name = name;
        this.billingAddress = billingAddress;
    }


}
