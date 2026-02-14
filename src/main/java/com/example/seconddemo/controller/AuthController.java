package com.example.seconddemo.controller;

import com.example.seconddemo.dto.SignUpDto;
import com.example.seconddemo.exception.InvalidArgumentException;
import com.example.seconddemo.exception.ProductNotFoundException;
import com.example.seconddemo.service.UserServiceInterface;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/api")
public class AuthController {

    private final UserServiceInterface userService;

    public AuthController(UserServiceInterface userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpDto signUpDto) throws ProductNotFoundException {
        return userService.userSignUp(signUpDto.getName(), signUpDto.getEmail(), signUpDto.getPhoneNumber(), signUpDto.getPassword());
    }

    @GetMapping("/signIn")
    public String signIn(@RequestBody SignUpDto signUpDto) throws ProductNotFoundException {
//        throw new InvalidArgumentException("Invalid email or password");
        return userService.userSignIn(signUpDto.getEmail(), signUpDto.getPassword());
    }
}
