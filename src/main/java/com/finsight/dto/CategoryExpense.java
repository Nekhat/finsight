package com.finsight.dto;

import java.math.BigDecimal;

public class CategoryExpense {

    private String categoryName;
    private BigDecimal totalAmount;

    //Constructors
    public CategoryExpense() {
    }

    public CategoryExpense(String categoryName, BigDecimal totalAmount) {
        this.categoryName = categoryName;
        this.totalAmount = totalAmount;
    }

    //Getters
    public String getCategoryName() {
        return categoryName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

}
