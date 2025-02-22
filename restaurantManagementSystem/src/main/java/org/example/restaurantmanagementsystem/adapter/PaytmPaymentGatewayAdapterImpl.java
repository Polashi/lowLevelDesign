package org.example.restaurantmanagementsystem.adapter;

import org.example.restaurantmanagementsystem.libraries.paytm.PaytmApi;
import org.example.restaurantmanagementsystem.libraries.paytm.PaytmPaymentResponse;
import org.example.restaurantmanagementsystem.model.Payment;
import org.example.restaurantmanagementsystem.model.PaymentStatus;

public class PaytmPaymentGatewayAdapterImpl implements PaymentGatewayAdapter{
    PaytmApi paytmApi = new PaytmApi();

    @Override
    public Payment makePayment(long billId, double amount) {
        PaytmPaymentResponse response = new PaytmPaymentResponse();
        Payment payment = new Payment();
        response = paytmApi.makePayment(billId, amount);
        payment.setBillId(response.getOrderId());
        payment.setStatus(PaymentStatus.valueOf(response.getPaymentStatus()));
        payment.setTransactionId(response.getTransactionId());
        return payment;
    }
}
