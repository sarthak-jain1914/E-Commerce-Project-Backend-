package com.example.seconddemo.Service;



import com.example.seconddemo.paymentgateways.InterfacePaymentGateway;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class paymentService implements PaymentServiceInterface {
    @Autowired
    private InterfacePaymentGateway paymentGateway;

    @Override
    public String initiatePayment(String email, String name , String phoneNumber, String orderId,
                                  Long amount){
        return paymentGateway.getPaymentGatewayLink(email, name, phoneNumber,orderId, amount);
    }
}
