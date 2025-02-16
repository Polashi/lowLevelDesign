package org.example.splitwise.entity;

import lombok.Data;

@Data
public class GroupExpense extends BaseModel{
    private Group group;
    private Expense expense;
}
