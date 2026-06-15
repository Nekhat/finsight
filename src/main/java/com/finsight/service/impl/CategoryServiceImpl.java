package com.finsight.service.impl;

import com.finsight.dto.CategoryCreateRequest;
import com.finsight.dto.CategoryResponse;
import com.finsight.dto.CategoryUpdateRequest;
import com.finsight.entity.Category;
import com.finsight.entity.User;
import com.finsight.repository.CategoryRepository;
import com.finsight.repository.TransactionRepository;
import com.finsight.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    public CategoryServiceImpl (CategoryRepository categoryRepository, TransactionRepository transactionRepository){
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public CategoryResponse createCategory(CategoryCreateRequest request, User user){

        if(request.getName() == null || request.getName().isBlank()){
            throw new IllegalArgumentException("Category name is required");
        }

        if(request.getType() == null){
            throw new IllegalArgumentException("Category type is required");
        }

        String name = request.getName().trim();

        if (categoryRepository.existsByNameIgnoreCaseAndIsGlobalTrue(name) ||
            categoryRepository.existsByNameIgnoreCaseAndUser(name, user)){
            throw new RuntimeException("Category with this name already exists");
        }

        Category category = new Category(
                name,
                request.getType(),
                false,
                user
        );

        Category savedCategory = categoryRepository.save(category);

        CategoryResponse categoryResponse = new CategoryResponse(
                savedCategory.getId(),
                savedCategory.getName(),
                savedCategory.getType(),
                savedCategory.isGlobal()
        );

        return categoryResponse;
    }

    @Override
    public List<CategoryResponse> getAllCategories(User user){

        List<Category> categoryList = categoryRepository.findByIsGlobalTrueOrUser(user);
        List<CategoryResponse> categoryResponseList = new ArrayList<>();
        for (Category category: categoryList)
        {
            CategoryResponse categoryResponse = new CategoryResponse(
                    category.getId(),
                    category.getName(),
                    category.getType(),
                    category.isGlobal()
            );
            categoryResponseList.add(categoryResponse);
        }

        return categoryResponseList;
    }

    @Override
    public CategoryResponse getCategoryById(Integer userId, Integer categoryId){

        Category category = categoryRepository.
                findByIdAndUserIdOrIdAndIsGlobalTrue(categoryId, userId, categoryId)
                .orElseThrow(() ->
                        new RuntimeException("Category not found or not accessible"));

        CategoryResponse categoryResponse = new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getType(),
                category.isGlobal()
        );

        return categoryResponse;

    }

    @Override
    public CategoryResponse updateCategory(Integer categoryId, CategoryUpdateRequest request, User user){

        Category category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new RuntimeException("Category not found"));

        if(category.isGlobal()) {
            throw new RuntimeException("Global categories cannot be modified");
        }

        if(!category.getUser().getId().equals (user.getId())){
            throw new RuntimeException("You are not allowed to modify this category");
        }

        if(request.getName() == null || request.getName().isBlank()){
            throw new RuntimeException("Category name is required");
        }

        String newName = request.getName().trim();

        if(categoryRepository.existsByNameIgnoreCaseAndIsGlobalTrue(newName) ||
                categoryRepository.existsByNameIgnoreCaseAndUserAndIdNot(newName, user, categoryId)){
            throw new RuntimeException("Category with this name already exists");
        }

        category.setName(newName);
        Category savedCategory = categoryRepository.save(category);

        CategoryResponse categoryResponse = new CategoryResponse(
                savedCategory.getId(),
                savedCategory.getName(),
                savedCategory.getType(),
                savedCategory.isGlobal()
        );
        return categoryResponse;
    }

    @Override
    public void deleteCategory(Integer categoryId, User user) {
        Category category = categoryRepository.findById(categoryId).
                orElseThrow(() -> new RuntimeException("Category not found"));

        if(category.isGlobal()){
            throw new RuntimeException("Global categories cannot be deleted");
        }

        if(!category.getUser().getId().equals (user.getId())){
            throw new RuntimeException("You are not allowed to modify this category");
        }

        if(transactionRepository.existsByCategory(category)){
            throw new RuntimeException("Cannot delete category with existing transactions");
        }

        categoryRepository.delete(category);
    }
}
