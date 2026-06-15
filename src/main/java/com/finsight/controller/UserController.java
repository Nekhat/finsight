package com.finsight.controller;

import com.finsight.dto.LoginRequest;
import com.finsight.dto.LoginResponse;
import com.finsight.dto.UserRegisterRequest;
import com.finsight.dto.UserResponse;
import com.finsight.entity.User;
import com.finsight.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(
            @RequestBody UserRegisterRequest request) {

        // DTO -> Entity
        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        User savedUser = userService.registerUser(user);

        // Entity -> DTO
        UserResponse response = new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(
            @RequestBody LoginRequest loginRequest) {

     User user = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

     LoginResponse response = new LoginResponse(
             user.getId(),
             user.getName(),
             user.getEmail()
     );

     return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
