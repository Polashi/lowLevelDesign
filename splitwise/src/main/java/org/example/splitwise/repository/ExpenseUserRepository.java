package org.example.splitwise.repository;

import org.example.splitwise.entity.ExpenseUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseUserRepository extends JpaRepository<ExpenseUser,Integer> {
}
