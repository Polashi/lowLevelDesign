package org.example.digitalwallet.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Transaction extends BaseModel{
    private int amount;
    private Date date;
    private User user;
    private PaymentType paymentType;
}
