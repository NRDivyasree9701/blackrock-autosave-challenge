package com.blackrock.autosave.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class AggregationRequest {

    private List<Transaction> transactions;

    @JsonProperty("kPeriods")
    private List<KPeriod> kPeriods;

    public AggregationRequest() {
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<KPeriod> getKPeriods() {
        return kPeriods;
    }

    public void setKPeriods(List<KPeriod> kPeriods) {
        this.kPeriods = kPeriods;
    }
}