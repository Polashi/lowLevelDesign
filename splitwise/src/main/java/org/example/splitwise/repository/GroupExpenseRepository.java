package org.example.splitwise.repository;

import org.example.splitwise.entity.GroupExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupExpenseRepository extends JpaRepository<GroupExpense, Integer> {
    List<GroupExpense> findByGroupId(int groupId);
}
