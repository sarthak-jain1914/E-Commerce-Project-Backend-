package com.example.seconddemo.Service;


import com.example.seconddemo.exception.ProductNotFoundException;
import com.razorpay.RazorpayException;

public interface ProductServiceInterface {
    String initiatePayment( String email, String name , String phoneNumber,
                                   String orderId, Long amount) throws RazorpayException, ProductNotFoundException;
}
