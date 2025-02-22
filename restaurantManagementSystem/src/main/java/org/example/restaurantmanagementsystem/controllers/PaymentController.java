package org.example.restaurantmanagementsystem.controllers;

import org.example.restaurantmanagementsystem.dtos.MakePaymentRequestDto;
import org.example.restaurantmanagementsystem.dtos.MakePaymentResponseDto;
import org.example.restaurantmanagementsystem.services.PaymentService;

public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public MakePaymentResponseDto makePayment(MakePaymentRequestDto makePaymentRequestDto) {
        return null;
    }
}