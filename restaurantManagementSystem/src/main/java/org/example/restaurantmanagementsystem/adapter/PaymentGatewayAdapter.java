package org.example.restaurantmanagementsystem.adapter;

import org.example.restaurantmanagementsystem.model.Payment;

public interface PaymentGatewayAdapter {
    Payment makePayment(long billId, double amount);
}
