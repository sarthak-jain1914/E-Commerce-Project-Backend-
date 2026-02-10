package com.example.seconddemo.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDTO {
    private String email;
    private String name;
    private String phoneNumber;
    private String orderId;
    private Long amount;
}
