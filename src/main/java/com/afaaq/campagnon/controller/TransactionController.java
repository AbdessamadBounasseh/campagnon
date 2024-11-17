package com.afaaq.campagnon.controller;

import com.afaaq.campagnon.dto.TransactionRequestDto;
import com.afaaq.campagnon.model.Campaign;
import com.afaaq.campagnon.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Map<String, String>> createTransaction(
            @RequestBody @Valid TransactionRequestDto request) {
        transactionService.createTransaction(request);
        return ResponseEntity.ok().body(Map.of("message", "Transaction confirmed"));
    }
}
