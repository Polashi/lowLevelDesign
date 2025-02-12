package org.example.ecommerce.model;

import lombok.Data;

@Data
public class OrderDetail extends BaseModel{
    private Order order;
    private Product product;
    private int quantity;
}
