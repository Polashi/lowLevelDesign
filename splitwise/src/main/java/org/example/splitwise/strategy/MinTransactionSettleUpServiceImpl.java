package org.example.splitwise.strategy;

import org.example.splitwise.entity.Expense;
import org.example.splitwise.entity.Transaction;

import java.util.List;

public class MinTransactionSettleUpServiceImpl implements SettleUpStrategy {
    @Override
    public List<Transaction> settleUpExpense(List<Expense> allExpenses) {
        return List.of();
    }
}
