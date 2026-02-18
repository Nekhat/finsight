package com.finsight.controller;

import com.finsight.dto.CategoryCreateRequest;
import com.finsight.dto.CategoryResponse;
import com.finsight.dto.CategoryUpdateRequest;
import com.finsight.dto.LoginRequest;
import com.finsight.entity.Category;
import com.finsight.entity.User;
import com.finsight.repository.UserRepository;
import com.finsight.service.CategoryService;
import com.finsight.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users/{userId}/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final UserService userService;

    public CategoryController(CategoryService categoryService, UserService userService){
        this.categoryService = categoryService;
        this.userService = userService;

    }

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(
            @PathVariable Integer userId,
            @RequestBody CategoryCreateRequest request){

        User user = getUserOrThrow(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(request, user));
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable Integer userId,
            @PathVariable Integer categoryId,
            @RequestBody CategoryUpdateRequest request){

        User user = getUserOrThrow(userId);

        return ResponseEntity.ok(categoryService.updateCategory(categoryId, request, user));
    }

    @GetMapping
    public  ResponseEntity<List<CategoryResponse>> getAllCategories(
            @PathVariable Integer userId){

        User user = getUserOrThrow(userId);

        return ResponseEntity.ok(categoryService.getAllCategories(user));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Integer userId,
            @PathVariable Integer categoryId){

        User user = getUserOrThrow(userId);

        categoryService.deleteCategory(categoryId, user);
        return ResponseEntity.noContent().build();
    }

    // Helper Method
    private User getUserOrThrow(Integer userId) {
        return userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

}
