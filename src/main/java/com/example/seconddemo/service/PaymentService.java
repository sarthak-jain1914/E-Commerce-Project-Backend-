package com.example.seconddemo.service;



import com.example.seconddemo.paymentgateways.InterfacePaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements PaymentServiceInterface {
    @Autowired
    private InterfacePaymentGateway paymentGateway;

    @Override
    public String initiatePayment(String email, String name , String phoneNumber, String orderId,
                                  Long amount){
        return paymentGateway.getPaymentGatewayLink(email, name, phoneNumber,orderId, amount);
    }
}
