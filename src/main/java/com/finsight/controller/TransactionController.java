package com.finsight.controller;

import com.finsight.dto.TransactionCreateRequest;
import com.finsight.dto.TransactionResponse;
import com.finsight.dto.TransactionUpdateRequest;
import com.finsight.entity.User;
import com.finsight.service.TransactionService;
import com.finsight.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final UserService userService;

    public TransactionController(TransactionService transactionService, UserService userService){
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(
            @PathVariable Integer userId,
            @RequestBody @Valid TransactionCreateRequest request){

        User user = getUserOrThrow(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.createTransaction(request, user));

    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> updateTransaction(
            @PathVariable Integer userId,
            @PathVariable Integer transactionId,
            @RequestBody @Valid TransactionUpdateRequest request){

        User user = getUserOrThrow(userId);

        return ResponseEntity.ok(transactionService.updateTransaction(transactionId, request, user));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTransactions(
            @PathVariable Integer userId){

        User user = getUserOrThrow(userId);

        return ResponseEntity.ok(transactionService.getAllTransactions(user));
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(
            @PathVariable Integer userId,
            @PathVariable Integer transactionId){

        User user = getUserOrThrow(userId);

        transactionService.deleteTransaction(transactionId, user);
        return ResponseEntity.noContent().build();

    }

    // Helper Method
    private User getUserOrThrow(Integer userId) {
        return userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
