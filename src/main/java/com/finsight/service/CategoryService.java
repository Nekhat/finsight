package com.finsight.service.impl;

import com.finsight.dto.CategoryCreateRequest;
import com.finsight.dto.CategoryUpdateRequest;
import com.finsight.entity.Category;
import com.finsight.entity.User;

import java.util.List;

public interface CategoryService {

    Category createCategory(CategoryCreateRequest request, User user);

    List<Category> getAllCategories(User user);

    Category updateCategory(Integer categoryId, CategoryUpdateRequest request, User user);

    Void deleteCategory(Integer categoryId, User user);

}
