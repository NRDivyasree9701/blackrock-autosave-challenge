package com.blackrock.autosave.model;

public class AggregationResult {

    private String start;
    private String end;
    private double totalRemnant;

    public AggregationResult() {}

    public AggregationResult(String start, String end, double totalRemnant) {
        this.start = start;
        this.end = end;
        this.totalRemnant = totalRemnant;
    }

    public String getStart() { return start; }
    public void setStart(String start) { this.start = start; }

    public String getEnd() { return end; }
    public void setEnd(String end) { this.end = end; }

    public double getTotalRemnant() { return totalRemnant; }
    public void setTotalRemnant(double totalRemnant) { this.totalRemnant = totalRemnant; }
}