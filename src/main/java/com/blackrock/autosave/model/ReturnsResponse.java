package com.blackrock.autosave.model;

import java.util.List;

public class ReturnsResponse {

    private double totalTransactionAmount;
    private double totalCeiling;
    private List<SavingsResult> savingsByDates;

    public ReturnsResponse() {}

    public ReturnsResponse(double totalTransactionAmount,
                           double totalCeiling,
                           List<SavingsResult> savingsByDates) {
        this.totalTransactionAmount = totalTransactionAmount;
        this.totalCeiling = totalCeiling;
        this.savingsByDates = savingsByDates;
    }

    public double getTotalTransactionAmount() {
        return totalTransactionAmount;
    }

    public void setTotalTransactionAmount(double totalTransactionAmount) {
        this.totalTransactionAmount = totalTransactionAmount;
    }

    public double getTotalCeiling() {
        return totalCeiling;
    }

    public void setTotalCeiling(double totalCeiling) {
        this.totalCeiling = totalCeiling;
    }

    public List<SavingsResult> getSavingsByDates() {
        return savingsByDates;
    }

    public void setSavingsByDates(List<SavingsResult> savingsByDates) {
        this.savingsByDates = savingsByDates;
    }
}