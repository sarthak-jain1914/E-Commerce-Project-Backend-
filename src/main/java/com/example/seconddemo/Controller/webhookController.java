package com.example.seconddemo.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.rowset.serial.SQLOutputImpl;

@RestController
public class webhookController {


    @PostMapping("/stripewebhook")
    public String receiveEvents(@RequestBody String events) {
        System.out.println(events);
        return events;
    }
}
