package com.example.seconddemo.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class paymentRequestDTO {
    private String email;
    private String name;
    private String phoneNumber;
    private String orderId;
    private Long amount;
}
