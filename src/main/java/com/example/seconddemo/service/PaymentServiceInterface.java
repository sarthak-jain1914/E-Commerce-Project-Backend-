package com.example.seconddemo.service;


import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentServiceInterface {
    String initiatePayment( String email, String name , String phoneNumber,
                                   String orderId, Long amount) throws RazorpayException, StripeException;
}
