package com.example.seconddemo.Controller;

import com.example.seconddemo.DTO.paymentRequestDTO;
import com.example.seconddemo.Service.paymentService;
import com.example.seconddemo.exception.ProductNotFoundException;
import com.razorpay.RazorpayException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class paymentController {

    private paymentService PaymentService;

    public paymentController(paymentService PaymentService) {
        this.PaymentService = PaymentService;
    }

    @PostMapping("/payment")
    public String initiatePayment(@RequestBody paymentRequestDTO PaymentRequestDTO) throws RazorpayException, ProductNotFoundException {
            return PaymentService.initiatePayment(PaymentRequestDTO.getEmail(), PaymentRequestDTO.getName(),
                    PaymentRequestDTO.getPhoneNumber(), PaymentRequestDTO.getOrderId(), PaymentRequestDTO.getAmount());

        }
}
