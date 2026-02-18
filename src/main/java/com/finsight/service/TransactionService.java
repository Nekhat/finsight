package com.finsight.service.impl;

import com.finsight.dto.TransactionCreateRequest;
import com.finsight.dto.TransactionResponse;
import com.finsight.dto.TransactionUpdateRequest;

import com.finsight.entity.User;

import java.util.List;

public interface TransactionService {
    TransactionResponse createTransaction(TransactionCreateRequest request, User user);
    TransactionResponse updateTransaction(TransactionUpdateRequest request, User user);
    List<TransactionResponse> getUserTransactions(User user);
    void deleteTransaction(Integer transactionId, User user);
}
