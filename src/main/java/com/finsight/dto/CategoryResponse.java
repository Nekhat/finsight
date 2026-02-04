package com.finsight.dto;

import com.finsight.entity.CategoryType;

public class CategoryResponse {

    private Integer id;
    private String name;
    private CategoryType type;
    private boolean global;

    public CategoryResponse(){
    }

    public CategoryResponse(Integer id, String name, CategoryType type, boolean global){
        this.id = id;
        this.name = name;
        this.type = type;
        this.global = global;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryType getType() {
        return type;
    }

    public boolean isGlobal() {
        return global;
    }
}
