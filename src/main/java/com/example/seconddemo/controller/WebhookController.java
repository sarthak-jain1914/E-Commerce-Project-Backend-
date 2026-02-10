package com.example.seconddemo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {


    @PostMapping("/stripewebhook")
    public String receiveEvents(@RequestBody String events) {
        System.out.println(events);
        return events;
    }
}
