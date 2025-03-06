package com.example.seconddemo.Service;



import com.example.seconddemo.paymentgateway.RazorpayPaymentGateway;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("paymentService")
public class paymentService implements ProductServiceInterface {

    @Autowired
    private RazorpayPaymentGateway razorpayPaymentGateway;

    @Override
    public String initiatePayment(String email, String name , String phoneNumber, String orderId,
                                  Long amount) throws RazorpayException {
        return razorpayPaymentGateway.getPaymentGatewayLink(email, name, phoneNumber,orderId, amount);
    }
}
