package com.scaler.paymentservice.services;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private PaymentGatewaySelector paymentGatewaySelector;

    public PaymentService(PaymentGatewaySelector paymentGatewaySelector) {
        this.paymentGatewaySelector = paymentGatewaySelector;
    }

    public void generatePaymentLink() {
        // Logic to update the DB when the payment starts

//        return paymentGatewaySelector
//                .getPaymentGateway()
//                .generatePaymentLink();
    }
}
