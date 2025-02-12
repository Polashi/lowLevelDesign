package org.example.ecommerce.model;

import lombok.Data;

@Data
public class HighDemandProduct extends BaseModel{
    private Product product;
    private int maxQuantityPerProduct;
}
