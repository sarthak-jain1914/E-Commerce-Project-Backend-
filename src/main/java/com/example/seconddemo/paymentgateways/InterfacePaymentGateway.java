package com.example.seconddemo.paymentgateways;

import com.razorpay.RazorpayException;

public interface InterfacePaymentGateway {
     String getPaymentGatewayLink(String email, String name , String phoneNumber,
                                 String orderId, Long amount);

}
