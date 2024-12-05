package com.scaler.paymentservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StripeWebhookController {

    @PostMapping("/webhook")
    public void webhook(@RequestBody Object object) {
        // Update the DB to payment done here
        System.out.println(object);
    }
}
