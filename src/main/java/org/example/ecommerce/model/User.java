package org.example.ecommerce.model;

import lombok.Data;

import java.util.List;

@Data
public class User extends BaseModel {
    private String name;
    private String email;
    private List<Address> addressList;
    private List<Order> orderList;
}
