package com.example.charter.controller;

import com.example.charter.service.RewardService;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest(RewardsController.class)
class RewardsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RewardService rewardService;

    @Test
    void testGetAllRewardsAPI() throws Exception {

        when(rewardService.getAllRewards()).thenReturn(List.of());

        mockMvc.perform(get("/api/rewards"))
                .andExpect(status().isOk());
    }
}