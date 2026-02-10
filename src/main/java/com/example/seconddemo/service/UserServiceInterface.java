package com.example.seconddemo.service;

import com.example.seconddemo.models.User;

public interface UserServiceInterface {
    public String userSignUp(String name, String email, String phoneNumber, String password);
    public String userSignIn(String email, String password);
}
