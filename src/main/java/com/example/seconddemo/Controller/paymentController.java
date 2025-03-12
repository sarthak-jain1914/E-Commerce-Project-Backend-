package com.example.seconddemo.Controller;

import com.example.seconddemo.DTO.paymentRequestDTO;
import com.example.seconddemo.Service.PaymentServiceInterface;
import com.example.seconddemo.Service.paymentService;
import com.example.seconddemo.exception.ProductNotFoundException;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class paymentController {

    @Autowired
    private PaymentServiceInterface PaymentService;



    @PostMapping("/payment")
    public String initiatePayment(@RequestBody paymentRequestDTO PaymentRequestDTO) throws RazorpayException, ProductNotFoundException, StripeException {
            return PaymentService.initiatePayment(PaymentRequestDTO.getEmail(), PaymentRequestDTO.getName(),
                    PaymentRequestDTO.getPhoneNumber(), PaymentRequestDTO.getOrderId(), PaymentRequestDTO.getAmount());

        }
}
