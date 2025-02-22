package org.example.restaurantmanagementsystem.libraries.razorpay;

import lombok.Data;
import org.example.restaurantmanagementsystem.model.PaymentStatus;

import java.util.Date;

@Data
public class RazorpayPaymentResponse {
    private String transactionId;
    private double transactionAmount;
    private Date transactionDate;
    private PaymentStatus paymentStatus;
    private long orderId;
}
