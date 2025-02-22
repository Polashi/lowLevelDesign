package org.example.restaurantmanagementsystem.service;

import org.example.restaurantmanagementsystem.adapter.PaymentGatewayAdapter;
import org.example.restaurantmanagementsystem.exception.InvalidBillException;
import org.example.restaurantmanagementsystem.model.Payment;
import org.example.restaurantmanagementsystem.repositories.BillRepository;

public class PaymentServiceImpl implements PaymentService {
    PaymentGatewayAdapter paymentGatewayAdapter;
    BillRepository billRepository;

    PaymentServiceImpl(PaymentGatewayAdapter paymentGatewayAdapter, BillRepository billRepository){
        this.paymentGatewayAdapter = paymentGatewayAdapter;
        this.billRepository = billRepository;
    }

    @Override
    public Payment makePayment(long billId) throws InvalidBillException {
        if(billRepository.existsById(billId)){
            throw new InvalidBillException("Invalid Bill");
        }
        return paymentGatewayAdapter.makePayment(billId,
                billRepository.findById(billId).get().getTotalAmount());
    }
}
