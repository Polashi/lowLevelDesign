package org.example.restaurantmanagementsystem.controller;

import org.example.restaurantmanagementsystem.dto.MakePaymentRequestDto;
import org.example.restaurantmanagementsystem.dto.MakePaymentResponseDto;
import org.example.restaurantmanagementsystem.dto.ResponseStatus;
import org.example.restaurantmanagementsystem.model.Payment;
import org.example.restaurantmanagementsystem.service.PaymentService;

public class PaymentController {
    private PaymentService paymentService;
    public PaymentController(PaymentService paymentService){
        this.paymentService = paymentService;
    }


    public MakePaymentResponseDto makePayment(MakePaymentRequestDto makePaymentRequestDto){
        MakePaymentResponseDto responseDto = new MakePaymentResponseDto();
        Payment payment = paymentService.makePayment(makePaymentRequestDto.getBillId());
        responseDto.setPaymentStatus(payment.getStatus());
        responseDto.setTransactionId(payment.getTransactionId());
        responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDto;
    }


}
