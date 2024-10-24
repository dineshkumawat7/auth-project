package com.ebit.auth.controller;

import com.ebit.auth.entity.User;
import com.ebit.auth.payload.ApiResponse;
import com.ebit.auth.payload.UserLoginDto;
import com.ebit.auth.payload.UserRegisterDto;
import com.ebit.auth.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/status")
    public String users(){
        return "Users access..";
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> createNewUser(@Valid @RequestBody UserRegisterDto userRegisterDto){
        User createdUser = userService.createUser(userRegisterDto);
        createdUser.setPassword("*******");
        ApiResponse<User> response = new ApiResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(true);
        response.setStatus(HttpStatus.CREATED);
        response.setMessage("new user register successfully");
        response.setData(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
