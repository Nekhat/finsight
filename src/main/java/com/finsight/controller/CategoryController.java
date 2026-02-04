package com.finsight.controller;

import com.finsight.dto.CategoryCreateRequest;
import com.finsight.dto.CategoryResponse;
import com.finsight.dto.CategoryUpdateRequest;
import com.finsight.dto.LoginRequest;
import com.finsight.entity.Category;
import com.finsight.entity.User;
import com.finsight.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(
            @RequestBody CategoryCreateRequest request,
            @AuthenticationPrincipal User user){

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(request, user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable Integer id,
            @RequestBody CategoryUpdateRequest request,
            @AuthenticationPrincipal User user){

        return ResponseEntity.ok(categoryService.updateCategory(id, request, user));
    }

    @GetMapping
    public  ResponseEntity<List<CategoryResponse>> getAllCategories(
            @AuthenticationPrincipal User user){

        return ResponseEntity.ok(categoryService.getAllCategories(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Integer id,
            @AuthenticationPrincipal User user){

        categoryService.deleteCategory(id, user);
        return ResponseEntity.noContent().build();
    }   

}
