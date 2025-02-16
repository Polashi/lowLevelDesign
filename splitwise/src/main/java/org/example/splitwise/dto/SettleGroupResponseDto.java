package org.example.splitwise.dto;

import lombok.Data;
import org.example.splitwise.entity.Transaction;

import java.util.List;

@Data
public class SettleGroupResponseDto {
    private List<Transaction> transactionList;
    private ResponseStatus responseStatus;
}
