package com.finsight.service;

import com.finsight.entity.User;

import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    User authenticate(String email, String password);
    Optional<User> getUserByEmail(String email);
    Optional<User> findById(Integer userId);
}
