package com.finsight.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionCreateRequest {
    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private LocalDate date;

    private String notes;

    @NotNull
    private Integer categoryId;

    public TransactionCreateRequest(){
    }

    //Getters

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDate(){
        return date;
    }

    public String getNotes(){
        return notes;
    }

    public Integer getCategoryId(){
        return categoryId;
    }

    //Setters

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
