package com.ebit.auth.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(){
        super("user already exists");
    }

    public UserAlreadyExistsException(String message){
        super(message);
    }
}
