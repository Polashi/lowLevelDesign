package org.example.restaurantmanagementsystem.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment extends BaseModel{
    private String transactionId;
    private PaymentStatus status;
    private long billId;
}
