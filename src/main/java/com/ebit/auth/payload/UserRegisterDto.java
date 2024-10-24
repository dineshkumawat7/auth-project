package com.ebit.auth.payload;

import jakarta.validation.constraints.*;

public class UserRegisterDto {

    @NotNull(message = "name is required")
    @NotBlank(message = "name is required")
    private String name;

    @Email(message = "invalid email")
    @NotNull(message = "email is required")
    @NotBlank(message = "email is required")
    private String email;

    @NotNull(message = "phone is required")
    @NotBlank(message = "phone is required")
    @Size(min = 10, max = 10, message = "invalid phone")
    private String phone;

    @NotNull(message = "password is required")
    @NotBlank(message = "password is required")
    @Size(min = 5, max = 10, message = "password must between 5 to 10 character")
    private String password;

    public UserRegisterDto() {

    }

    public UserRegisterDto(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
