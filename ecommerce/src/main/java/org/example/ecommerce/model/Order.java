package org.example.ecommerce.model;

import lombok.Data;

import java.util.List;

@Data
public class Order extends BaseModel{
    private User user;
    private Address deliveryAddress;
    private List<OrderDetail> orderDetails;
    private OrderStatus orderStatus;
}
