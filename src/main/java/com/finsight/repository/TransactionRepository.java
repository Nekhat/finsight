package com.finsight.repository;

import com.finsight.entity.Category;
import com.finsight.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Optional<Transaction> findByIdAndUserId(Integer transactionId, Integer userId);
    List<Transaction> findAllByUserIdOrderByDateDesc(Integer userId);
    boolean existsByCategory(Category category);

}
