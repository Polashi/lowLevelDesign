package org.example.restaurantmanagementsystem.repositories;

import org.example.restaurantmanagementsystem.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface BillRepository {
    Optional<Bill> findById(Long id);
    Bill save(Bill bill);
}
