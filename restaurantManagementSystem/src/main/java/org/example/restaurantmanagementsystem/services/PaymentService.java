package org.example.restaurantmanagementsystem.services;

import org.example.restaurantmanagementsystem.exceptions.InvalidBillException;
import org.example.restaurantmanagementsystem.models.Payment;

public interface PaymentService {

    Payment makePayment(long billId) throws InvalidBillException;
}