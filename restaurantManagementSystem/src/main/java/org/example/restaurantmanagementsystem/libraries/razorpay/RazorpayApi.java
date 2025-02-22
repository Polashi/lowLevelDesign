package org.example.restaurantmanagementsystem.libraries.razorpay;

import org.example.restaurantmanagementsystem.model.PaymentStatus;

import java.util.Date;
import java.util.UUID;

public class RazorpayApi {
    public RazorpayPaymentResponse makePayment(long orderId, double amount){
        RazorpayPaymentResponse paymentResponse = new RazorpayPaymentResponse();
        paymentResponse.setOrderId(orderId);
        paymentResponse.setTransactionAmount(amount);
        paymentResponse.setPaymentStatus(PaymentStatus.SUCCESS);
        paymentResponse.setTransactionDate(new Date());
        paymentResponse.setTransactionId(UUID.randomUUID().toString());

        return paymentResponse;
    }
}
