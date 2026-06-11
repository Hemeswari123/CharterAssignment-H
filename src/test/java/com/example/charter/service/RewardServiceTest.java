package com.example.charter.service;

import com.example.charter.dto.CustomerRewardResponse;
import com.example.charter.entity.Transaction;
import com.example.charter.repository.TransactionRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RewardServiceTest {

    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private RewardService rewardService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateRewardsForCustomer() {

        // Arrange (mock data)
        List<Transaction> transactions = List.of(
                new Transaction(1L, "John", 120.0, LocalDate.now()),
                new Transaction(1L, "John", 75.0, LocalDate.now())
        );

        when(repository.findAll()).thenReturn(transactions);

        // Act
        List<CustomerRewardResponse> result =
                rewardService.getAllRewards();

        // Assert
        assertEquals(1, result.size());
        assertEquals(115, result.get(0).getTotalRewards());
    }

    @Test
    void testCalculatePoints() {

        assertEquals(0, rewardService.calculatePoints(40));
        assertEquals(25, rewardService.calculatePoints(75));
        assertEquals(90, rewardService.calculatePoints(120));
    }
}