package com.finsight.repository;

import com.finsight.entity.Category;
import com.finsight.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByNameIgnoreCaseAndIsGlobalTrue(String name);
    boolean existsByNameIgnoreCaseAndUser(String name, User user);
    List<Category> findByIsGlobalTrueOrUser(User user);
    boolean existsByNameIgnoreCaseAndUserAndIdNot(String name, User user, Integer id); // Does another category with this name exist for this user, excluding this category?
}
