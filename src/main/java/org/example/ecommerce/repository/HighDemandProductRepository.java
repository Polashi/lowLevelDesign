package org.example.ecommerce.repository;

import org.example.ecommerce.model.HighDemandProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HighDemandProductRepository extends JpaRepository<HighDemandProduct, Integer> {
    boolean findHighDemandProductByProductIdExists(int productId);

    Optional<HighDemandProduct> findByProductId(int productId);
}
