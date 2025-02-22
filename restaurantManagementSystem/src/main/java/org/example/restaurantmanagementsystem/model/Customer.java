package org.example.restaurantmanagementsystem.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Customer extends BaseModel{
    private String name;
    private int vistCount;
    private List<Bill> billList;
}
