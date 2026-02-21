package com.blackrock.autosave.model;

public class Transaction {

    private String date;
    private double amount;
    private double ceiling;
    private double remanent;   

    public Transaction() {}

    public Transaction(String date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getCeiling() {
        return ceiling;
    }

    public void setCeiling(double ceiling) {
        this.ceiling = ceiling;
    }

    public double getRemanent() {
        return remanent;
    }

    public void setRemanent(double remanent) {
        this.remanent = remanent;
    }
    
    private boolean inkPeriod;

    public boolean isInkPeriod() {
        return inkPeriod;
    }

    public void setInkPeriod(boolean inkPeriod) {
        this.inkPeriod = inkPeriod;
    }
}