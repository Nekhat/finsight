package com.finsight.service;

import com.finsight.dto.CategoryCreateRequest;
import com.finsight.dto.CategoryResponse;
import com.finsight.dto.CategoryUpdateRequest;
import com.finsight.entity.User;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CategoryCreateRequest request, User user);

    List<CategoryResponse> getAllCategories(User user);

    CategoryResponse updateCategory(Integer categoryId, CategoryUpdateRequest request, User user);

    void deleteCategory(Integer categoryId, User user);

}
