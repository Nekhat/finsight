package com.finsight.dto;

import com.finsight.entity.CategoryType;

public class CategoryCreateRequest {

    private String name;
    private CategoryType type;

    public CategoryCreateRequest(){
    }

    public String getName(){
        return name;
    }

    public CategoryType getType(){
        return type;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }
}
