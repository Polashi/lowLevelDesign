package org.example.digitalwallet.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends BaseModel{
    private String name;
    private String email;
    private String phoneNumber;
}
