package org.example.ecommerce.controller;

import org.example.ecommerce.dto.PlaceOrderRequestDto;
import org.example.ecommerce.dto.PlaceOrderResponseDto;
import org.example.ecommerce.dto.ResponseStatus;
import org.example.ecommerce.service.OrderService;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public PlaceOrderResponseDto placeOrder(PlaceOrderRequestDto placeOrderRequestDto) throws Exception {
        PlaceOrderResponseDto placeOrderResponseDto = new PlaceOrderResponseDto();
        placeOrderResponseDto.setOrder(orderService.placeOrder(placeOrderRequestDto.getUserId(), placeOrderRequestDto.getAddressId(), placeOrderRequestDto.getOrderDetails()));
        placeOrderResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        return placeOrderResponseDto;
    }
}
