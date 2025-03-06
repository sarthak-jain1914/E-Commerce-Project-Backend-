package com.example.seconddemo.Configuration;


import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayConfiguration {

    @Value("${razorpay.id}")
    private String razorpayId;

    @Value("${razorpay.secret}")
    private String razorpaySecret;

    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {
        try {
            return new RazorpayClient(razorpayId, razorpaySecret);
        } catch (RazorpayException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }
}
