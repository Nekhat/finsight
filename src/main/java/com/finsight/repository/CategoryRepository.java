package com.finsight.repository;

import com.finsight.entity.Category;
import com.finsight.entity.Transaction;
import com.finsight.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByNameIgnoreCaseAndIsGlobalTrue(String name);
    boolean existsByNameIgnoreCaseAndUser(String name, User user);
    List<Category> findByIsGlobalTrueOrUser(User user);
    boolean existsByNameIgnoreCaseAndUserAndIdNot(String name, User user, Integer id);// Does another category with this name exist for this user, excluding this category?
    Optional<Category> findByIdAndUserIdOrIdAndIsGlobalTrue(Integer id1, Integer userId, Integer id2);
}
