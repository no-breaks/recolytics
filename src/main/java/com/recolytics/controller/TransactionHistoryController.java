package com.recolytics.controller;

import com.recolytics.model.Transaction;
import com.recolytics.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class TransactionHistoryController {

    private final TransactionRepository transactionRepository;

    @GetMapping
    public List<Transaction> getHistory() {
        return transactionRepository.findAll();
    }
}
