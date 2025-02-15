package org.example.digitalwallet.dto;

import lombok.Data;
import org.springframework.stereotype.Controller;

@Data
public class TransferRequestDTO {
    private int senderId;
    private int receiverId;
    private int amount;

}
