package com.kwikkart.kwikkart.model;

import java.util.HashMap;
import java.util.Map;

public class CreditCard {
    private int cardNumber;
    private int cvv;
    private String expirationDate;
    private String billingAddress;

    public CreditCard(){
        //data snapshot
    }

    public CreditCard(int cardNumber, int cvv, String expirationDate, String billingAddress){
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.billingAddress = billingAddress;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> creditCardValues = new HashMap<>();
        creditCardValues.put("cardNumber", this.cardNumber);
        creditCardValues.put("cvv", this.cvv);
        creditCardValues.put("expirationDate", this.expirationDate);
        creditCardValues.put("billingAddress", this.billingAddress);

        return creditCardValues;
    }

}
