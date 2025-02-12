package org.example.ecommerce.model;

import lombok.Data;

@Data
public class Product extends BaseModel{
    private String name;
    private String description;
    private double price;
}
