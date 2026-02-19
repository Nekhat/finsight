package com.finsight.service.impl;

import com.finsight.dto.TransactionCreateRequest;
import com.finsight.dto.TransactionResponse;
import com.finsight.dto.TransactionUpdateRequest;
import com.finsight.entity.Category;
import com.finsight.entity.Transaction;
import com.finsight.entity.User;
import com.finsight.repository.CategoryRepository;
import com.finsight.repository.TransactionRepository;
import com.finsight.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionResponse createTransaction(TransactionCreateRequest request, User user) {
        Integer categoryId = request.getCategoryId();
        Integer userId = user.getId();

        Category category = categoryRepository
                .findByIdAndUserIdOrIdAndIsGlobalTrue(categoryId, userId, categoryId)
                .orElseThrow(() ->
                        new RuntimeException("Category not found or not accessible"));
        if (request.getDate().isAfter(LocalDate.now())){
            throw new RuntimeException("Date cannot be in the future");
        }
        Transaction transaction = new Transaction(
                request.getAmount(),
                request.getDate(),
                request.getNotes(),
                user,
                category);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return mapToResponse(savedTransaction);
    }

    @Override
    public TransactionResponse updateTransaction(Integer transactionId, TransactionUpdateRequest request, User user) {
        Transaction transaction = transactionRepository.
                findByIdAndUserId(transactionId, user.getId())
                .orElseThrow(() ->
                        new RuntimeException("Invalid transaction or user"));

        if(request.getAmount()!=null){
            transaction.updateAmount(request.getAmount());
        }

        if(request.getDate()!=null){
            if (request.getDate().isAfter(LocalDate.now())) {
                throw new RuntimeException("Date cannot be in the future");
            }
            transaction.updateDate(request.getDate());
        }

        if(request.getNotes()!=null){
            transaction.updateNotes(request.getNotes());
        }

        return mapToResponse(transaction);
    }

    @Override
    public List<TransactionResponse> getAllTransactions(User user) {
         List<Transaction> userTransactionsList = transactionRepository.findAllByUserIdOrderByDateDesc(user.getId());

        return userTransactionsList.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public void deleteTransaction(Integer transactionId, User user) {
        Transaction transaction = transactionRepository.findByIdAndUserId(transactionId, user.getId()).
                orElseThrow(() ->
                        new RuntimeException("Transaction not found"));

        transactionRepository.delete(transaction);

    }

    // Mapping Helper
    private TransactionResponse mapToResponse(Transaction transaction){

        return new TransactionResponse(transaction.getId(),
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getNotes(),
                transaction.getCategory().getName(),
                transaction.getType().name(),
                transaction.getCreatedAt(),
                transaction.getUpdatedAt());
    }
}
