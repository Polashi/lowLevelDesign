package org.example.ecommerce.dto;

import lombok.Data;
import java.util.List;
import org.springframework.data.util.Pair;

@Data
public class PlaceOrderRequestDto {
    private int userId;
    private int addressId;
    private List<Pair<Integer, Integer>> orderDetails;
}
