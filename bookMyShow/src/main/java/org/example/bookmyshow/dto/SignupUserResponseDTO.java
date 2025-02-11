package org.example.bookmyshow.dto;

import lombok.Data;

@Data
public class SignupUserResponseDTO {
    private ResponseStatus responseStatus;
    private String name;
    private String email;
    private long userId;
}
