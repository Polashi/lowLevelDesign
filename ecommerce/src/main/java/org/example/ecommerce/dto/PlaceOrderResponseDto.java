package org.example.ecommerce.dto;

import lombok.Data;
import org.example.ecommerce.model.Order;

@Data
public class PlaceOrderResponseDto {
    private Order order;
    private ResponseStatus responseStatus;
}
