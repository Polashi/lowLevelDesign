package org.example.ecommerce.service;


import org.example.ecommerce.model.Order;
import org.springframework.data.util.Pair;

import java.util.List;

public interface OrderService {

    public Order placeOrder(int userId, int addressId, List<Pair<Integer, Integer>> orderDetails) throws Exception;
}
