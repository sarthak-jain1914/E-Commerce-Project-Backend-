package com.example.seconddemo.controller;

import com.example.seconddemo.dto.PaymentRequestDTO;
import com.example.seconddemo.service.PaymentServiceInterface;
import com.example.seconddemo.exception.ProductNotFoundException;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentServiceInterface PaymentService;



    @PostMapping("/payment")
    public String initiatePayment(@RequestBody PaymentRequestDTO PaymentRequestDTO) throws RazorpayException, ProductNotFoundException, StripeException {
            return PaymentService.initiatePayment(PaymentRequestDTO.getEmail(), PaymentRequestDTO.getName(),
                    PaymentRequestDTO.getPhoneNumber(), PaymentRequestDTO.getOrderId(), PaymentRequestDTO.getAmount());

        }
}
