package org.example.ecommerce.repository;

import org.example.ecommerce.model.Order;
import org.example.ecommerce.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Override
    Optional<OrderDetail> findById(Integer id);

    @Override
    OrderDetail save(OrderDetail orderDetail);

    Order findByOrderId(Integer orderId);

}
