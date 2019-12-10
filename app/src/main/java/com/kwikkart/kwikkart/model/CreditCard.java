package com.kwikkart.kwikkart.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The credit card class handles credit card information
 */
public class CreditCard {
    private int cardNumber;
    private int cvv;
    private String expirationDate;
    private String billingAddress;

    /**
     * CreditCard constructor default
     */
    public CreditCard(){
        //data snapshot
    }

    /**
     * CreditCard
     * initialized constructor
     * @param cardNumber int
     * @param cvv int
     * @param expirationDate String
     * @param billingAddress String
     */
    public CreditCard(int cardNumber, int cvv, String expirationDate, String billingAddress){
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.billingAddress = billingAddress;
    }

    /**
     *  toMap
     * @return Map<String, Object>
     */
    public Map<String, Object> toMap(){
        Map<String, Object> creditCardValues = new HashMap<>();
        creditCardValues.put("cardNumber", this.cardNumber);
        creditCardValues.put("cvv", this.cvv);
        creditCardValues.put("expirationDate", this.expirationDate);
        creditCardValues.put("billingAddress", this.billingAddress);

        return creditCardValues;
    }

}
