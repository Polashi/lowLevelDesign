package org.example.restaurantmanagementsystem.libraries.paytm;

import lombok.Data;
import org.example.restaurantmanagementsystem.model.PaymentStatus;

import java.util.Date;

@Data
public class PaytmPaymentResponse {
    private String transactionId;
    private double transactionAmount;
    private Date transactionDate;
    private String paymentStatus;
    private long orderId;
}
