package org.example.restaurantmanagementsystem.models;

import java.util.List;

public class Customer extends BaseModel{
    private String name;
    private int visitCount;
    private List<Bill> bills;

}