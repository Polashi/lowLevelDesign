package org.example.splitwise.strategy;

import org.example.splitwise.entity.Expense;
import org.example.splitwise.entity.Transaction;

import java.util.List;

public interface SettleUpStrategy {
    List<Transaction> settleUpExpense(List<Expense> allExpenses);
}
