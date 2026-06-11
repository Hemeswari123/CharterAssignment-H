package com.example.charter.dto;

import java.util.List;

import com.example.charter.dto.MonthlyReward;


public class CustomerRewardResponse {

    private Long customerId;
    private String customerName;
    private List<MonthlyReward> monthlyRewards;
    
    private long totalRewards;

    public CustomerRewardResponse(
            Long customerId,
            String customerName,
            List<MonthlyReward> monthlyRewards,
            long totalRewards) {

        this.customerId = customerId;
        this.customerName = customerName;
        this.monthlyRewards = monthlyRewards;
        this.totalRewards = totalRewards;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<MonthlyReward> getMonthlyRewards() {
        return monthlyRewards;
    }

    public long getTotalRewards() {
        return totalRewards;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setMonthlyRewards(List<MonthlyReward> monthlyRewards) {
        this.monthlyRewards = monthlyRewards;
    }

    public void setTotalRewards(long totalRewards) {
        this.totalRewards = totalRewards;
    }
}