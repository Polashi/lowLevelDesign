package org.example.splitwise.entity;

import lombok.Data;


@Data
public class ExpenseUser extends BaseModel{
    private User user;
    private Expense expense;
    private double amount;
    private ExpenseType expenseType;
}
