package org.example.splitwise.entity;

import lombok.Data;

@Data
public class User extends BaseModel{
    private String name;
    private String email;
    private long phoneNumber;
}
