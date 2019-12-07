package com.kwikkart.kwikkart.model;

import java.util.HashMap;
import java.util.Map;

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


    public Map<String, Object> toMap(){
        Map<String, Object> userValues = new HashMap<>();
        userValues.put("email", this.email);
        userValues.put("name", this.name);
        userValues.put("billingAddress", this.billingAddress);

        return userValues;
    }


}
