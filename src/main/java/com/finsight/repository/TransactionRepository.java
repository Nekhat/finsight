package com.finsight.repository;

import com.finsight.dto.CategoryExpense;
import com.finsight.entity.Category;
import com.finsight.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    Optional<Transaction> findByIdAndUserId(Integer transactionId, Integer userId);
    List<Transaction> findAllByUserIdOrderByDateDesc(Integer userId);
    boolean existsByCategory(Category category);

    // Methods & Queries for Dashboard

    @Query("SELECT COALESCE(SUM(t.amount), 0) " +
            "FROM Transaction t " +
            "WHERE t.user.id = :userId " +
            "AND t.category.type = com.finsight.entity.CategoryType.INCOME " +
            "AND t.date BETWEEN :startDate AND :endDate")
    BigDecimal getTotalIncome(@Param("userId") Integer userId,
                              @Param("startDate") LocalDate startDate,
                              @Param("endDate") LocalDate endDate);

    @Query("SELECT COALESCE(SUM(t.amount), 0) " +
            "FROM Transaction t " +
            "WHERE t.user.id = :userId " +
            "AND t.category.type = com.finsight.entity.CategoryType.EXPENSE " +
            "AND t.date BETWEEN :startDate AND :endDate")
    BigDecimal getTotalExpense(@Param("userId") Integer userId,
                               @Param("startDate") LocalDate startDate,
                               @Param("endDate") LocalDate endDate);

    @Query("SELECT new com.finsight.dto.CategoryExpense(t.category.name, COALESCE(SUM(t.amount), 0)) " +
            "FROM Transaction t " +
            "WHERE t.user.id = :userId " +
            "AND t.category.type = com.finsight.entity.CategoryType.EXPENSE " +
            "AND t.date BETWEEN :startDate AND :endDate " +
            "GROUP BY t.category.name")
    List<CategoryExpense> getExpenseByCategory(@Param("userId") Integer userId,
                                               @Param("startDate") LocalDate startDate,
                                               @Param("endDate") LocalDate endDate);


    List<Transaction> findTop5ByUserIdAndDateBetweenOrderByDateDesc(Integer userId, LocalDate startDate, LocalDate endDate);

}
