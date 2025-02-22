package org.example.restaurantmanagementsystem.adapters;

import org.example.restaurantmanagementsystem.models.Payment;

public interface PaymentGatewayAdapter {

    Payment makePayment(long billId, double amount);
}