package org.example.restaurantmanagementsystem.dto;

import lombok.Data;
import org.example.restaurantmanagementsystem.model.PaymentStatus;

@Data
public class MakePaymentResponseDto {
    private ResponseStatus responseStatus;
    private String transactionId;
    private PaymentStatus paymentStatus;
}
