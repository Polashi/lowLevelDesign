package org.example.splitwise.service;

import org.example.splitwise.entity.*;
import org.example.splitwise.exception.GroupNotFoundException;
import org.example.splitwise.repository.ExpenseRepository;
import org.example.splitwise.repository.ExpenseUserRepository;
import org.example.splitwise.repository.GroupExpenseRepository;
import org.example.splitwise.repository.GroupRepository;
import org.example.splitwise.strategy.MinTransactionSettleUpServiceImpl;
import org.example.splitwise.strategy.SettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupService {
    private GroupRepository groupRepository;
    private GroupExpenseRepository groupExpenseRepository;
    private SettleUpStrategy settleUpStrategy;
    private ExpenseUserRepository expenseUserRepository;
    private ExpenseRepository expenseRepository;

    public GroupService(GroupRepository groupRepository,
                        GroupExpenseRepository groupExpenseRepository,
                        ExpenseUserRepository expenseUserRepository,
                        ExpenseRepository expenseRepository){
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.expenseUserRepository = expenseUserRepository;
        this.groupExpenseRepository = groupExpenseRepository;
        this.settleUpStrategy = new MinTransactionSettleUpServiceImpl();
    }

    public List<Transaction> settleGroup(int groupId) throws GroupNotFoundException{
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if(optionalGroup.isEmpty()){
            throw new GroupNotFoundException("Group not found");
        }
        List<GroupExpense> groupExpenseList = groupExpenseRepository.findByGroupId(groupId);
        List<Expense> allExpenses = new ArrayList<>();
        for(GroupExpense groupExpense: groupExpenseList){
            allExpenses.add(groupExpense.getExpense());
        }
        List<Transaction> transactionList = settleUpStrategy.settleUpExpense(allExpenses);
        saveAllTransactions(transactionList);
        return transactionList;

    }

    private void saveAllTransactions(List<Transaction> transactionList) {
        for(Transaction transaction: transactionList){
            ExpenseUser expenseUserPaidFrom = new ExpenseUser();
            expenseUserPaidFrom.setAmount(transaction.getAmount());
            expenseUserPaidFrom.setUser(transaction.getPaymentFrom());
            expenseUserPaidFrom.setExpenseType(ExpenseType.PAID);

            ExpenseUser expenseUserPaidTo = new ExpenseUser();
            expenseUserPaidTo.setAmount(transaction.getAmount());
            expenseUserPaidTo.setUser(transaction.getPaymentTo());
            expenseUserPaidTo.setExpenseType(ExpenseType.OWED);

            Expense expense = new Expense();
            expense.setAmount(transaction.getAmount()*2); // paidFrom + paidTo
            expense.setAddedAt(new Date());
            expense.setDescription("Settle all expenses");

            expenseUserPaidFrom.setExpense(expense);
            expenseUserPaidTo.setExpense(expense);

            expenseUserPaidFrom = expenseUserRepository.save(expenseUserPaidFrom);
            expenseUserPaidTo = expenseUserRepository.save(expenseUserPaidTo);
            expense.setExpenseUserList(List.of(expenseUserPaidFrom,expenseUserPaidTo));
            expense = expenseRepository.save(expense);

        }
    }
}
