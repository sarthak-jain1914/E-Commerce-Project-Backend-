package com.example.seconddemo.service;

import com.example.seconddemo.configuration.BCryptEncode;
import com.example.seconddemo.configuration.JwtUtil;
import com.example.seconddemo.exception.InvalidArgumentException;
import com.example.seconddemo.exception.UserNotFoundException;
import com.example.seconddemo.models.User;
import com.example.seconddemo.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceInterfaceImpl implements UserServiceInterface {

    private final UserRepo userRepo;
    private final BCryptEncode bCryptEncode;
    private final JwtUtil jwtUtil;

    public UserServiceInterfaceImpl(JwtUtil jwtUtil, UserRepo userRepo, BCryptEncode bCryptEncode) {
        this.userRepo = userRepo;
        this.bCryptEncode = bCryptEncode;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String userSignUp(String name, String email, String phoneNumber, String password) {
       Optional<User> user = userRepo.findByEmail(email);
       if(user.isPresent()){
           throw new InvalidArgumentException("User with email " + email + " already exists please try to signup with different email");
       }
       else{
           User newUser = new User();
           newUser.setName(name);
           newUser.setEmail(email);
           newUser.setPhoneNumber(phoneNumber);
           newUser.setPassword(bCryptEncode.encode(password));
           userRepo.save(newUser);
       }
       return "User signed up successfully";
    }

    @Override
    public String userSignIn(String email, String password) {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent() && bCryptEncode.matches(password, user.get().getPassword())){
                String token = jwtUtil.generateToken(user.get().getId());
                return token;
        }
        else{
            throw new IllegalArgumentException("Invalid password or user not exist with email: " + email);
        }
    }
}

