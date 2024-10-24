package com.ebit.auth.controller;

import com.ebit.auth.entity.User;
import com.ebit.auth.payload.ApiResponse;
import com.ebit.auth.payload.UserLoginDto;
import com.ebit.auth.repository.UserRepo;
import com.ebit.auth.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> createNewUser(@Valid @RequestBody UserLoginDto userLoginDto){
        userService.login(userLoginDto);
        ApiResponse<User> response = new ApiResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(true);
        response.setStatus(HttpStatus.CREATED);
        response.setMessage("you are login successfully");
        response.setData(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/session")
    public ResponseEntity<ApiResponse<HttpSession>> getSessionInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        ApiResponse<HttpSession> response = new ApiResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setSuccess(true);
        response.setStatus(HttpStatus.CREATED);
        response.setMessage("session info");
        response.setData(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
