
package com.blackrock.autosave.model;

import java.util.List;

public class FilterRequest {

    private double wage;
    private List<QPeriod> q;
    private List<PPeriod> p;
    private List<KPeriod> k;
    private List<Transaction> transactions;

    public double getWage() { return wage; }
    public void setWage(double wage) { this.wage = wage; }

    public List<QPeriod> getQ() { return q; }
    public void setQ(List<QPeriod> q) { this.q = q; }

    public List<PPeriod> getP() { return p; }
    public void setP(List<PPeriod> p) { this.p = p; }

    public List<KPeriod> getK() { return k; }
    public void setK(List<KPeriod> k) { this.k = k; }

    public List<Transaction> getTransactions() { return transactions; }
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}