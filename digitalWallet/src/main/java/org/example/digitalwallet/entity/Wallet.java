package org.example.digitalwallet.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Wallet extends BaseModel{
    private User user;
    private int balance;
}
