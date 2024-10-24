package com.ebit.auth.service.impl;

import com.ebit.auth.entity.Role;
import com.ebit.auth.entity.User;
import com.ebit.auth.exception.ResourceNotFoundException;
import com.ebit.auth.exception.UserAlreadyExistsException;
import com.ebit.auth.payload.UserLoginDto;
import com.ebit.auth.payload.UserRegisterDto;
import com.ebit.auth.repository.RoleRepo;
import com.ebit.auth.repository.UserRepo;
import com.ebit.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User createUser(UserRegisterDto userRegisterDto) {
        if(userRepo.findByEmail(userRegisterDto.getEmail()).isPresent()){
            throw new UserAlreadyExistsException("an user already exists with this email");
        }
        User newUser = new User();
        newUser.setId(UUID.randomUUID().toString());
        newUser.setName(userRegisterDto.getName());
        newUser.setEmail(userRegisterDto.getEmail());
        newUser.setPhone(userRegisterDto.getPhone());
        newUser.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        newUser.setActive(true);
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());
        if(roleRepo.findByName("ROLE_USER").isEmpty()){
            createUserRole();
        }
        newUser.setRoles((Arrays.asList(roleRepo.findByName("ROLE_USER").get())));
        return userRepo.save(newUser);
    }

    private Role createUserRole(){
        Role role = new Role();
        role.setId(UUID.randomUUID().toString());
        role.setName("ROLE_USER");
        return roleRepo.save(role);
    }

    @Override
    public User login(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));
        if(authentication.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return userRepo.findByEmail(userLoginDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("user not found with email: " + userLoginDto.getEmail()));
        }
        throw new BadCredentialsException("invalid email or password");
    }

}
