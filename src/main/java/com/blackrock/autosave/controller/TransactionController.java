package com.blackrock.autosave.controller;

import com.blackrock.autosave.model.*;
import com.blackrock.autosave.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/process")
    public List<Transaction> process(@RequestBody ProcessRequest request) {

    	
        service.processTransactions(
                request.getTransactions(),
                request.getQPeriods(),
                request.getPPeriods()
        );

        return request.getTransactions();
    }
    
    @PostMapping("/aggregate")
    public List<AggregationResult> aggregate(@RequestBody AggregationRequest request) {
    	System.out.println("K: " + request.getKPeriods());
        return service.aggregateByKPeriod(
                request.getTransactions(),
                request.getKPeriods()
        );
    }
    
    @PostMapping("/blackrock/challenge/v1/transactions:parse")
    public List<Transaction> parseTransactions(
            @RequestBody List<Transaction> transactions) {

        for (Transaction t : transactions) {

            double ceiling = Math.ceil(t.getAmount() / 100.0) * 100;
            double remanent = ceiling - t.getAmount();

            t.setCeiling(ceiling);
            t.setRemanent(remanent);
        }

        return transactions;
    }
    
    @PostMapping("/blackrock/challenge/v1/transactions:validator")
    public ValidationResponse validate(
            @RequestBody ValidationRequest request) {

        return service.validateTransactions(
                request.getWage(),
                request.getTransactions()
        );
    }
    @PostMapping("/blackrock/challenge/v1/transactions:filter")
    public ValidationResponse filter(@RequestBody FilterRequest request) {
        return service.filterTransactions(request);
    }
    
    
    @PostMapping("/blackrock/challenge/v1/returns:nps")
    public ReturnsResponse calculateNps(@RequestBody ReturnsRequest request) {
        return service.calculateReturns(request, true);
    }

    @PostMapping("/blackrock/challenge/v1/returns:index")
    public ReturnsResponse calculateIndex(@RequestBody ReturnsRequest request) {
        return service.calculateReturns(request, false);
    }
    
    
    @GetMapping("/blackrock/challenge/v1/performance")
    public PerformanceResponse performance() {

        long start = System.currentTimeMillis();

        // simulate execution time
        long end = System.currentTimeMillis();
        long duration = end - start;

        // Format time as HH:mm:ss.SSS
        String formattedTime = String.format("00:00:00.%03d", duration);

        // Memory usage
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        double usedMB = usedMemory / (1024.0 * 1024.0);
        String memoryFormatted = String.format("%.2f MB", usedMB);

        // Thread count
        int threads = Thread.activeCount();

        return new PerformanceResponse(formattedTime, memoryFormatted, threads);
    }
    
    
    
    
    
    
}