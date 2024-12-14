package com.afaaq.campagnon.service;

import com.afaaq.campagnon.dto.TransactionRequestDto;

public interface ITransactionService {
    void createTransaction(TransactionRequestDto transactionRequestDto);
}
