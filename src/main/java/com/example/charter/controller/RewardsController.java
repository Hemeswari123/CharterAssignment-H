package com.example.charter.controller;


import com.example.charter.dto.CustomerRewardResponse;
import com.example.charter.service.RewardService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rewards")

public class RewardsController {

    private final RewardService rewardService;

    public RewardsController(
            RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/getAllrewards")
    public List<CustomerRewardResponse>getAllRewards() {
     return rewardService.getAllRewards();
    }

    @GetMapping("/{customerId}")    
    public CustomerRewardResponse getCustomerRewards(@PathVariable Long customerId) {
           return rewardService.getCustomerRewards(customerId);
    }
}