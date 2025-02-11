package org.example.bookmyshow.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    private final BCryptPasswordEncoder encoder;


    public PasswordService(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public String encryptPassword(String rawPassword){
        return encoder.encode(rawPassword);
    }

    public boolean comparePassword(String actualPassword, String inputPassword){
        return encoder.matches(actualPassword, inputPassword);
    }
}
