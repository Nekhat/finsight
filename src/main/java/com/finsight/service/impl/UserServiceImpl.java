package com.finsight.service.impl;

import com.finsight.entity.User;
import com.finsight.repository.UserRepository;
import com.finsight.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl  implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    @Override
    public User registerUser(User user) {
        if (user.getName() == null || user.getName().isBlank()
                || user.getEmail() == null || user.getEmail().isBlank()
                || user.getPassword() == null || user.getPassword().isBlank()) {
            throw new IllegalArgumentException("Name, email, and password are required");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }

        if(user.getPassword().length()<8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }

        return userRepository.save(user);

    }

    @Override
    public User authenticate(String email, String password) {
        if(email == null || email.isBlank()
            || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Email and password are required");
        }

        Optional<User> optionalUser= userRepository.findByEmail(email);
        User user = optionalUser.orElseThrow(() ->
                new RuntimeException("Invalid email or password"));
        if(!user.getPassword().equals(password)){
            throw new RuntimeException("Invalid email or password");
        }

        return user;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Integer userId)
    {
        return userRepository.findById(userId);
    }
}
