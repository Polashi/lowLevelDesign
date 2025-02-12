package org.example.ecommerce.model;

import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class Inventory extends BaseModel{
    @OneToMany
    private Product product;
    private int quantity;
}
