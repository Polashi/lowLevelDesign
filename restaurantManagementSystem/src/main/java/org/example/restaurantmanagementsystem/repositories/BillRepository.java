package org.example.restaurantmanagementsystem.repositories;

import org.example.restaurantmanagementsystem.models.Bill;

import java.util.Optional;

public interface BillRepository {
    Bill save(Bill bill);
    Optional<Bill> findById(long billId);
}