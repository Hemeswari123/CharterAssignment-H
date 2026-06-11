package com.example.charter.service;

import com.example.charter.constants.*;
import com.example.charter.dto.CustomerRewardResponse;
import com.example.charter.dto.MonthlyReward;
import com.example.charter.entity.Transaction;
import com.example.charter.exception.ResourceNotFoundException;
import com.example.charter.repository.TransactionRepository;

import org.springframework.stereotype.Service;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RewardService {

    private final TransactionRepository repository;

    public RewardService(TransactionRepository repository) {
        this.repository = repository;
    }
    public List<CustomerRewardResponse> getAllRewards() {

        List<Transaction> transactions = repository.findAll();
        Map<Long, List<Transaction>> customerTransactions =transactions.stream().collect(Collectors.groupingBy(
                                Transaction::getCustomerId));
        List<CustomerRewardResponse> responses = new ArrayList<>();
        for (Map.Entry<Long, List<Transaction>> entry : customerTransactions.entrySet()) {
            responses.add(buildCustomerRewardResponse(entry.getKey(),entry.getValue()));
        }
        return responses;
    }

    public CustomerRewardResponse getCustomerRewards(Long customerId) {
        List<Transaction> transactions = repository.findByCustomerId(customerId);
        if (transactions.isEmpty()) {
        	throw new ResourceNotFoundException("Customer not found: " + customerId);
        }
        return buildCustomerRewardResponse(customerId, transactions);
    }

    private CustomerRewardResponse buildCustomerRewardResponse(Long customerId,List<Transaction> transactions) {

        String customerName =transactions.get(0).getCustomerName();
        Map<Month, Long> monthlyRewards =
                transactions.stream().collect(Collectors.groupingBy(t -> t.getTransactionDate().getMonth(),
                         Collectors.summingLong( t -> calculatePoints( t.getAmount()))));
        List<MonthlyReward> monthlyList =monthlyRewards.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey()).map(entry ->
                         new MonthlyReward(entry.getKey().name(),entry.getValue())) .toList();
        long totalRewards =monthlyRewards.values().stream().mapToLong(Long::longValue).sum();
        return new CustomerRewardResponse(customerId,customerName,monthlyList,totalRewards);
        
    }

    public long calculatePoints(double amount) {
        if (amount > Constants.input1) {
            return (long) ((amount - Constants.input1) * 
            		Constants.multiplyFactor + Constants.input2);
        }
        if (amount > Constants.input2) {
            return (long) (amount - Constants.input2);
        }
        return 0;
    }
}