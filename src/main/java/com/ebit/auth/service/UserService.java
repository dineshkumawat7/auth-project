package com.ebit.auth.service;

import com.ebit.auth.entity.User;
import com.ebit.auth.payload.UserLoginDto;
import com.ebit.auth.payload.UserRegisterDto;
import org.springframework.security.core.Authentication;

public interface UserService {
    User createUser(UserRegisterDto userRegisterDto);
    User login(UserLoginDto userLoginDto);
}
