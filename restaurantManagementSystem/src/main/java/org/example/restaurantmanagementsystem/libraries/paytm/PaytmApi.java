package org.example.restaurantmanagementsystem.libraries.paytm;

import org.example.restaurantmanagementsystem.model.PaymentStatus;

import java.util.Date;
import java.util.UUID;

public class PaytmApi {
    public PaytmPaymentResponse makePayment(long orderId, double amount){
        PaytmPaymentResponse paymentResponse = new PaytmPaymentResponse();

        paymentResponse.setOrderId(orderId);
        paymentResponse.setTransactionAmount(amount);
        paymentResponse.setPaymentStatus(PaymentStatus.SUCCESS);
        paymentResponse.setTransactionDate(new Date());
        paymentResponse.setTransactionId(UUID.randomUUID().toString());

        return paymentResponse;
    }
}
