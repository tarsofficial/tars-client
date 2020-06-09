package com.vasmajom.tars.model;

public class Wallet {

    private String currency;
    private String address;

    public Wallet(String currency, String address) {
        this.currency = currency;
        this.address = address;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
