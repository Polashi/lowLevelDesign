package org.example.ecommerce.repository;

import org.example.ecommerce.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Override
    Optional<Inventory> findById(Integer productId);
    

    @Override
    Inventory save(Inventory inventory);

    Optional<Inventory> findByProduct_Id(int productId);
}
