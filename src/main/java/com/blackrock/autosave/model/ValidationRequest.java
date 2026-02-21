package com.blackrock.autosave.model;

import java.util.List;

public class ValidationRequest {

    private double wage;
    private List<Transaction> transactions;

    public ValidationRequest() {}

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}