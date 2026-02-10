package com.example.seconddemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDto {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;
}
