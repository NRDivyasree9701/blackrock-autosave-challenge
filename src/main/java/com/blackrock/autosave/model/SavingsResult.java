package com.blackrock.autosave.model;

public class SavingsResult {

    private String start;
    private String end;
    private double amount;
    private double profit;
    private double taxBenefit;

    public SavingsResult() {}

    public SavingsResult(String start, String end, double amount,
                         double profit, double taxBenefit) {
        this.start = start;
        this.end = end;
        this.amount = amount;
        this.profit = profit;
        this.taxBenefit = taxBenefit;
    }

    public String getStart() { return start; }
    public String getEnd() { return end; }
    public double getAmount() { return amount; }
    public double getProfit() { return profit; }
    public double getTaxBenefit() { return taxBenefit; }

    public void setStart(String start) { this.start = start; }
    public void setEnd(String end) { this.end = end; }
    public void setAmount(double amount) { this.amount = amount; }
    public void setProfit(double profit) { this.profit = profit; }
    public void setTaxBenefit(double taxBenefit) { this.taxBenefit = taxBenefit; }
}