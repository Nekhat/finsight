package com.finsight.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RecentTransaction {

    private Integer transactionId;

    private String categoryName;

    private String type;

    private BigDecimal amount;

    private LocalDate date;

    //Constructors
    public RecentTransaction() {
    }

    public RecentTransaction(Integer transactionId, String categoryName, String type, BigDecimal amount, LocalDate date) {
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.categoryName = categoryName;
    }

    //Getters
    public Integer getTransactionId() {
        return transactionId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
