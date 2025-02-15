package org.example.digitalwallet.dto;

import lombok.Data;
import org.example.digitalwallet.entity.TransactionStatus;

@Data
public class FundRequestDTO {
    private int userId;
    private int amount;
}
