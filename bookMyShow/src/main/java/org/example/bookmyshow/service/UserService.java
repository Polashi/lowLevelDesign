package org.example.bookmyshow.service;

import org.example.bookmyshow.model.User;
import org.example.bookmyshow.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordService passwordService;

    public UserService(UserRepository userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }


    public User signupUser(String name, String email, String password) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(!optionalUser.isEmpty()){
            throw new Exception("User already exists");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        //Encrypt the password before saving
        String encryptedPassword = passwordService.encryptPassword(password);
        user.setPassword(encryptedPassword);

        return userRepository.save(user);

    }


    public boolean login(String email, String password) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new Exception("User does not exist");
        }

        User user = optionalUser.get();
        if(passwordService.comparePassword(user.getPassword(),password)){
            return true;
        }else{
            return false;
        }
    }
}
