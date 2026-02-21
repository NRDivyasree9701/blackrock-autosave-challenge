package com.blackrock.autosave.service;

import org.springframework.stereotype.Service;

import com.blackrock.autosave.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

	public void processTransactions(
	        List<Transaction> transactions,
	        List<QPeriod> qPeriods,
	        List<PPeriod> pPeriods) {

	    for (Transaction t : transactions) {

	        double ceiling = Math.ceil(t.getAmount() / 100.0) * 100;
	        double remanent = ceiling - t.getAmount();

	        if (qPeriods != null) {
	            for (QPeriod q : qPeriods) {
	                LocalDateTime start = LocalDateTime.parse(q.getStart());
	                LocalDateTime end = LocalDateTime.parse(q.getEnd());

	                if (isInRange(LocalDateTime.parse(t.getDate()), start, end)) {
	                    remanent = q.getFixed();
	                }
	            }
	        }

	        if (pPeriods != null) {
	            for (PPeriod p : pPeriods) {
	                LocalDateTime start = LocalDateTime.parse(p.getStart());
	                LocalDateTime end = LocalDateTime.parse(p.getEnd());

	                if (isInRange(LocalDateTime.parse(t.getDate()), start, end)) {
	                    remanent += p.getExtra();
	                }
	            }
	        }

	        t.setCeiling(ceiling);
	        t.setRemanent(remanent);
	    }
	}

    public List<AggregationResult> aggregateByKPeriod(
            List<Transaction> transactions,
            List<KPeriod> kPeriods) {

        List<AggregationResult> results = new ArrayList<>();
        
        if (kPeriods == null) {
            return results;
        }

        for (KPeriod k : kPeriods) {

            double sum = 0;

            LocalDateTime start = LocalDateTime.parse(k.getStart());
            LocalDateTime end = LocalDateTime.parse(k.getEnd());

            for (Transaction t : transactions) {

                LocalDateTime txnDate = LocalDateTime.parse(t.getDate());

                if (isInRange(txnDate, start, end)) {
                    sum += t.getRemanent();
                }
            }

            results.add(new AggregationResult(
                    k.getStart(),
                    k.getEnd(),
                    sum
            ));
        }

        return results;
    }

    private boolean isInRange(
            LocalDateTime date,
            LocalDateTime start,
            LocalDateTime end) {

        return !date.isBefore(start) && !date.isAfter(end);
    }
    
    
    public ValidationResponse validateTransactions(
            double wage,
            List<Transaction> transactions) {

        ValidationResponse response = new ValidationResponse();

        double totalInvestment = 0;
        List<String> seenDates = new ArrayList<>();

        for (Transaction t : transactions) {

            // Negative check
            if (t.getAmount() < 0) {
                response.getInvalid().add(t);
                continue;
            }

            // Duplicate check (by date)
            if (seenDates.contains(t.getDate())) {
                response.getDuplicate().add(t);
                continue;
            }

            seenDates.add(t.getDate());

            totalInvestment += t.getRemanent();

            response.getValid().add(t);
        }

        // Wage check
        if (totalInvestment > wage) {
            response.getInvalid().addAll(response.getValid());
            response.getValid().clear();
        }

        return response;
    }
    
    
    
    public ValidationResponse filterTransactions(FilterRequest request) {

        List<Transaction> valid = new ArrayList<>();
        List<Transaction> invalid = new ArrayList<>();

        List<String> seen = new ArrayList<>();

        for (Transaction t : request.getTransactions()) {

            // Calculate ceiling & remanent
            double ceiling = Math.ceil(t.getAmount() / 100.0) * 100;
            double remanent = ceiling - t.getAmount();

            t.setCeiling(ceiling);
            t.setRemanent(remanent);

            // Negative check
            if (t.getAmount() < 0) {
                invalid.add(t);
                continue;
            }

            // Duplicate check
            if (seen.contains(t.getDate())) {
                invalid.add(t);
                continue;
            }

            seen.add(t.getDate());

            // K period check
            boolean inK = false;
            if (request.getK() != null) {
                for (KPeriod k : request.getK()) {
                    LocalDateTime start = LocalDateTime.parse(k.getStart());
                    LocalDateTime end = LocalDateTime.parse(k.getEnd());
                    LocalDateTime date = LocalDateTime.parse(t.getDate());
                    if (!date.isBefore(start) && !date.isAfter(end)) {
                        inK = true;
                    }
                }
            }

            t.setInkPeriod(inK);
            valid.add(t);
        }

        ValidationResponse response = new ValidationResponse();
        response.setValid(valid);
        response.setInvalid(invalid);
        return response;
    }
    
    public ReturnsResponse calculateReturns(ReturnsRequest request, boolean isNps) {

        // Step 1: apply q and p logic
        processTransactions(request.getTransactions(), request.getQ(), request.getP());

        double totalAmount = 0;
        double totalCeiling = 0;

        for (Transaction t : request.getTransactions()) {
            if (t.getAmount() > 0) {
                totalAmount += t.getAmount();
                totalCeiling += t.getCeiling();
            }
        }

        double rate = isNps ? 0.0711 : 0.1449;

        int years = 60 - request.getAge();
        if (years < 0) years = 5;

        List<SavingsResult> results = new ArrayList<>();

        for (KPeriod k : request.getK()) {

            double invested = 0;

            LocalDateTime start = LocalDateTime.parse(k.getStart());
            LocalDateTime end = LocalDateTime.parse(k.getEnd());

            for (Transaction t : request.getTransactions()) {

                LocalDateTime txnDate = LocalDateTime.parse(t.getDate());

                if (!txnDate.isBefore(start) && !txnDate.isAfter(end)) {
                    invested += t.getRemanent();
                }
            }

            double futureValue = invested * Math.pow((1 + rate), years);
            double realValue = futureValue / Math.pow((1 + request.getInflation()/100), years);
            double profit = realValue - invested;

            double taxBenefit = 0;

            if (isNps) {
                double annualIncome = request.getWage() * 12;
                double deduction = Math.min(invested,
                        Math.min(annualIncome * 0.10, 200000));
                taxBenefit = deduction * 0.10;
            }

            results.add(new SavingsResult(
                    k.getStart(),
                    k.getEnd(),
                    invested,
                    Math.round(profit * 100.0) / 100.0,
                    Math.round(taxBenefit * 100.0) / 100.0
            ));
        }

        return new ReturnsResponse(totalAmount, totalCeiling, results);
    }
    
}