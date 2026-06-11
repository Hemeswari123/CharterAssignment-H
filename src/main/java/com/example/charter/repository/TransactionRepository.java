package com.example.charter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.charter.entity.Transaction;

import java.util.List;

public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCustomerId(Long customerId);
}