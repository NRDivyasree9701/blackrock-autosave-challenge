package com.blackrock.autosave.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProcessRequest {

    private List<Transaction> transactions;
    @JsonProperty("qPeriods")
    private List<QPeriod> qPeriods;
    @JsonProperty("pPeriods")
    private List<PPeriod> pPeriods;

    public ProcessRequest() {
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<QPeriod> getQPeriods() {
        return qPeriods;
    }

    public void setQPeriods(List<QPeriod> qPeriods) {
        this.qPeriods = qPeriods;
    }

    public List<PPeriod> getPPeriods() {
        return pPeriods;
    }

    public void setPPeriods(List<PPeriod> pPeriods) {
        this.pPeriods = pPeriods;
    }
}