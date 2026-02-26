package com.finsight.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DashboardResponse {

    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal balance;
    private List<CategoryExpense> expenseByCategory = new ArrayList<>();
    private List<RecentTransaction> recentTransactions = new ArrayList<>();

    //Constructors

    public DashboardResponse() {
    }

    public DashboardResponse(BigDecimal totalIncome, BigDecimal totalExpense, BigDecimal balance, List<CategoryExpense> expenseByCategory, List<RecentTransaction> recentTransactions) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.balance = balance;
        this.expenseByCategory = expenseByCategory;
        this.recentTransactions = recentTransactions;
    }

    //Getters
    public BigDecimal getTotalExpense() {
        return totalExpense;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<CategoryExpense> getExpenseByCategory() {
        return expenseByCategory;
    }

    public List<RecentTransaction> getRecentTransactions() {
        return recentTransactions;
    }
}
