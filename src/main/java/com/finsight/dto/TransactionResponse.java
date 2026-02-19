package com.finsight.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TransactionResponse {

    private Integer id;
    private BigDecimal amount;
    private LocalDate date;
    private String notes;
    private String categoryName;
    private String categoryType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TransactionResponse(){
    }

    public TransactionResponse(Integer id, BigDecimal amount, LocalDate date, String notes,
                               String categoryName, String categoryType, LocalDateTime createdAt,
                               LocalDateTime updatedAt) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.notes = notes;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters

    public Integer getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
