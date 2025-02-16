package org.example.splitwise.entity;

import lombok.Data;

@Data
public class Transaction extends BaseModel{
    private User paymentFrom;
    private User paymentTo;
    private double amount;
}
