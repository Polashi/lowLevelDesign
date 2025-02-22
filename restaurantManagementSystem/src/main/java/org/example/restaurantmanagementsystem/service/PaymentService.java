package org.example.restaurantmanagementsystem.service;

import org.example.restaurantmanagementsystem.exception.InvalidBillException;
import org.example.restaurantmanagementsystem.model.Payment;

public interface PaymentService {
    Payment makePayment(long billId) throws InvalidBillException;
}
