package org.example.bookmyshow.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private boolean loggedInFlag;
    private ResponseStatus responseStatus;
}
