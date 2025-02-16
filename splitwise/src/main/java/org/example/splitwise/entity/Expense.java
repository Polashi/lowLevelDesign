package org.example.splitwise.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Expense extends BaseModel{
    private Date addedAt;
    private double amount;
    private String description;
    private List<ExpenseUser> expenseUserList;
}
