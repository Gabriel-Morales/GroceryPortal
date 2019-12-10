package com.kwikkart.kwikkart.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User class handles User object functions and methods
 */
public class User {

    private String email;
    private String name;
    private String billingAddress;
    private String preference;
    private String phoneNumber;
    private List<Order> orderHistory;

    /**
     * User
     * default constructor
     */
    public User()
    {
        // Default constructor required for calls to DataSnapshot
    }

    /**
     * User
     * initialized constructor
     * @param email String
     * @param name String
     * @param billingAddress String
     * @param phoneNumber String
     * @param preference String
     */
    public User(String email, String name, String billingAddress, String phoneNumber, String preference)
    {
        this.email = email;
        this.name = name;
        this.billingAddress = billingAddress;
        this.phoneNumber = phoneNumber;
        this.preference = preference;
        this.orderHistory = new ArrayList<>();
    }

    /**
     * getOrderHistory
     * @return List<Order>
     */
    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    /**
     * toMap
     * @return Map<String, Object>
     */
    public Map<String, Object> toMap(){
        Map<String, Object> userValues = new HashMap<>();
        userValues.put("email", this.email);
        userValues.put("name", this.name);
        userValues.put("billingAddress", this.billingAddress);
        userValues.put("phoneNumber", this.phoneNumber);
        userValues.put("preference", this.preference);
        userValues.put("orderHistory", null);

        return userValues;
    }

    /**
     * getEmail
     * @return String
     */
    public String getEmail()
    {
        return email;
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
     * getBillingAddress
     * @return String
     */
    public String getBillingAddress()
    {
        return billingAddress;
    }

    /**
     * getPhoneNumber
     * @return String
     */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /**
     * getPreference
     * @return String
     */
    public String getPreference()
    {
        return  preference;
    }

    /**
     * setName
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setEmail
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * setBillingAddress
     * @param billingAddress String
     */
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * setPreference
     * @param preference String
     */
    public void setPreference(String preference) {
        this.preference = preference;
    }

    /**
     * setPhoneNumber
     * @param phoneNumber String
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
